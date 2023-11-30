package com.mayank.presentation.features.detailscreen

import androidx.lifecycle.viewModelScope
import com.mayank.domain.usecases.GetCharacterByIdUseCase
import com.mayank.presentation.base.BaseViewModel
import com.mayank.presentation.features.homescreen.mappers.CharacterMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(private val characterByIdUseCase: GetCharacterByIdUseCase,
                                                   private val characterMapper: CharacterMapper) :
    BaseViewModel<CharacterDetailViewState, CharacterDetailViewIntent, CharacterDetailSideEffect>() {

    private fun fetchCharacterList(id: Int){
        viewModelScope.launch {
            characterByIdUseCase(id).onStart {
                _state.emit(CharacterDetailViewState.Loading)
            }.catch {
                _state.emit(CharacterDetailViewState.Error(it))
            }.collect{
                _state.emit(CharacterDetailViewState.Success(characterMapper.mapFromModel(it)))
            }
        }
    }
    override fun sendIntent(intent: CharacterDetailViewIntent) {
        when(intent){
            is CharacterDetailViewIntent.LoadData -> {
                fetchCharacterList(intent.id)
            }
        }
    }
}