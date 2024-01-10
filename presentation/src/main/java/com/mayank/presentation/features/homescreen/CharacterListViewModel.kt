package com.mayank.presentation.features.homescreen

import androidx.lifecycle.viewModelScope
import com.mayank.domain.models.CharacterListModel
import com.mayank.domain.usecases.GetCharactersUseCase
import com.mayank.presentation.base.BaseViewModel
import com.mayank.presentation.mappers.CharacterListMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
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
            getCharactersUseCase().collectLatest { result ->
                when {
                    result.isSuccess -> {
                        state.emit(
                            CharacterListViewState.Success(
                                characterListMapper.mapFromModel(
                                    result.getOrDefault(
                                        CharacterListModel(listOf())
                                    )
                                )
                            )
                        )
                    }

                    result.isFailure -> {
                        state.emit(CharacterListViewState.Error(result.exceptionOrNull()!!))

                    }
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