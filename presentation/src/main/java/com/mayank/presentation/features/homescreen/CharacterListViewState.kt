package com.mayank.presentation.features.homescreen

import com.mayank.presentation.base.SideEffect
import com.mayank.presentation.base.ViewIntent
import com.mayank.presentation.base.ViewState
import com.mayank.presentation.models.CharacterList

sealed interface CharacterListViewState: ViewState {
    object Loading: CharacterListViewState

    data class Success(val data: CharacterList) : CharacterListViewState

    data class Error(val throwable: Throwable): CharacterListViewState
}

sealed interface CharacterListViewIntent: ViewIntent {
    object LoadData: CharacterListViewIntent

    class OnCharacterClick(val id: Int): CharacterListViewIntent
}

sealed interface CharacterListSideEffect: SideEffect {
    class NavigateToDetails(val id: Int) : CharacterListSideEffect
}