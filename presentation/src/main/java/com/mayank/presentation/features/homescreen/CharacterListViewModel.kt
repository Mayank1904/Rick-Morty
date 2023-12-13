package com.mayank.presentation.features.homescreen

import androidx.lifecycle.viewModelScope
import com.mayank.domain.usecases.GetCharactersUseCase
import com.mayank.presentation.base.BaseViewModel
import com.mayank.presentation.mappers.CharacterListMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(private val getCharactersUseCase: GetCharactersUseCase,
                                                 private val characterListMapper: CharacterListMapper
) :
    BaseViewModel<CharacterListViewState, CharacterListViewIntent, CharacterListSideEffect>() {

    private fun fetchCharacterList(){
        viewModelScope.launch {
            getCharactersUseCase().onStart {
                state.emit(CharacterListViewState.Loading)
            }.catch {
                state.emit(CharacterListViewState.Error(it))
            }.collect{
                state.emit(CharacterListViewState.Success(characterListMapper.mapFromModel(it)))
            }
        }
    }

    private fun navigateToDetails(id: Int) {
        viewModelScope.launch {
            sideEffect.emit(CharacterListSideEffect.NavigateToDetails(id))
        }

    }
    override fun sendIntent(intent: CharacterListViewIntent) {
        when(intent){
            is CharacterListViewIntent.LoadData -> fetchCharacterList()
            is CharacterListViewIntent.OnCharacterClick -> navigateToDetails(intent.id)
        }
    }
}