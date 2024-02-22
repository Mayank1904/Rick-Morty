package com.mayank.presentation.features.homescreen

import androidx.lifecycle.viewModelScope
import com.mayank.domain.Result
import com.mayank.domain.usecases.GetCharactersUseCase
import com.mayank.presentation.base.BaseViewModel
import com.mayank.presentation.di.IODispatcher
import com.mayank.presentation.di.MainDispatcher
import com.mayank.presentation.mappers.CharacterListMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val characterListMapper: CharacterListMapper,
    @IODispatcher var ioDispatcher: CoroutineDispatcher,
    @MainDispatcher var mainDispatcher: CoroutineDispatcher

) :
    BaseViewModel<CharacterListViewState, CharacterListViewIntent, CharacterListSideEffect>() {

    init {
        sendIntent(CharacterListViewIntent.LoadData)
    }

    private fun fetchCharacterList() {
        viewModelScope.launch(ioDispatcher) {
            when (val result = getCharactersUseCase()) {
                is Result.Success -> {
                    characterListMapper.map(
                        result.data
                    ).let {
                        withContext(mainDispatcher) {
                            state.emit(
                                CharacterListViewState.Success(it)
                            )
                        }
                    }

                }

                is Result.Error -> {
                    withContext(mainDispatcher) {
                        state.emit(CharacterListViewState.Error(result.exception))
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