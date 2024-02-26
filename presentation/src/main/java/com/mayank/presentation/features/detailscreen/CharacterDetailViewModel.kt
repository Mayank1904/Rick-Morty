package com.mayank.presentation.features.detailscreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.mayank.domain.Result
import com.mayank.domain.usecases.GetCharacterByIdUseCase
import com.mayank.presentation.base.BaseViewModel
import com.mayank.presentation.constant.Constant
import com.mayank.presentation.di.IODispatcher
import com.mayank.presentation.mappers.CharacterMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val characterByIdUseCase: GetCharacterByIdUseCase,
    private val characterMapper: CharacterMapper,
    savedStateHandle: SavedStateHandle,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
) :
    BaseViewModel<CharacterDetailViewState, CharacterDetailViewIntent, CharacterDetailSideEffect>() {

    init {
        val id = savedStateHandle.get<Int>(Constant.characterId)
        sendIntent(CharacterDetailViewIntent.LoadData(id ?: 0))
    }

    private fun fetchCharacterList(id: Int) =
        viewModelScope.launch(ioDispatcher) {
            when (val result = characterByIdUseCase(id)) {
                is Result.Success -> {
                    val mappedData = characterMapper.map(result.data)
                            viewModelScope.launch {
                                state.emit(
                                    CharacterDetailViewState.Success(mappedData)
                                )
                            }
                }
                is Result.Error -> viewModelScope.launch {
                    state.emit(
                        CharacterDetailViewState.Error(
                            result.exception
                        )
                    )
                }
            }
        }


    override fun sendIntent(intent: CharacterDetailViewIntent) {
        when (intent) {
            is CharacterDetailViewIntent.LoadData -> {
                fetchCharacterList(intent.id)
            }
        }
    }

    override fun createInitialState() = CharacterDetailViewState.Loading
}