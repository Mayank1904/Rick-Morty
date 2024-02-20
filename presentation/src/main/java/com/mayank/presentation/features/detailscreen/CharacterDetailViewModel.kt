package com.mayank.presentation.features.detailscreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mayank.domain.Result
import com.mayank.domain.usecases.GetCharacterByIdUseCase
import com.mayank.presentation.base.MVI
import com.mayank.presentation.base.mvi
import com.mayank.presentation.constant.Constant
import com.mayank.presentation.mappers.CharacterMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val characterByIdUseCase: GetCharacterByIdUseCase,
    private val characterMapper: CharacterMapper,
    savedStateHandle: SavedStateHandle
) : ViewModel(),
    MVI<CharacterDetailViewState, CharacterDetailViewIntent, CharacterDetailSideEffect> by mvi(initialState()) {

    init {
        val id = savedStateHandle.get<Int>(Constant.characterId)
        sendIntent(CharacterDetailViewIntent.LoadData(id ?: 0))
    }

    private fun fetchCharacterList(id: Int) =
        viewModelScope.launch {
            when (val result = characterByIdUseCase(id)) {
                is Result.Success -> updateViewState {
                    CharacterDetailViewState.Success(
                        characterMapper.map(result.data)
                    )
                }

                is Result.Error -> updateViewState {CharacterDetailViewState.Error(result.exception)
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

}
private fun initialState() = CharacterDetailViewState.Loading
