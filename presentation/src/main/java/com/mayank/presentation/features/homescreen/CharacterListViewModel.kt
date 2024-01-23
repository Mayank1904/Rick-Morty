package com.mayank.presentation.features.homescreen

import androidx.lifecycle.viewModelScope
import com.mayank.domain.Result
import com.mayank.domain.usecases.GetCharactersUseCase
import com.mayank.presentation.base.BaseViewModel
import com.mayank.presentation.mappers.CharacterListMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val characterListMapper: CharacterListMapper
) :
    BaseViewModel<CharacterListViewState, CharacterListViewIntent, CharacterListSideEffect>() {

    private fun fetchCharacterList() {
        viewModelScope.launch {
            when (val result = getCharactersUseCase()) {
                is Result.Success -> {
                    state.emit(
                        CharacterListViewState.Success(
                            characterListMapper.map(
                                result.data
                            )
                        )
                    )
                }

                is Result.Error -> {
                    state.emit(CharacterListViewState.Error(result.exception))
                }
            }
        }
    }

    private fun navigateToDetails(id: Int) {
        viewModelScope.launch {
            sideEffect.emit(CharacterListSideEffect.NavigateToDetails(id))
        }

    }

    override fun sendIntent(intent: CharacterListViewIntent) {
        when (intent) {
            is CharacterListViewIntent.LoadData -> fetchCharacterList()
            is CharacterListViewIntent.OnCharacterClick -> navigateToDetails(intent.id)
        }
    }

    override fun createInitialState() = CharacterListViewState.Loading
}