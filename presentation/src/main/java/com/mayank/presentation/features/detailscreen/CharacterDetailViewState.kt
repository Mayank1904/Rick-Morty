package com.mayank.presentation.features.detailscreen

import com.mayank.presentation.base.SideEffect
import com.mayank.presentation.base.ViewIntent
import com.mayank.presentation.base.ViewState
import com.mayank.presentation.models.CharacterItem

sealed interface CharacterDetailViewState: ViewState {
    object Loading: CharacterDetailViewState

    class Success(val data: CharacterItem) : CharacterDetailViewState

    class Error(val throwable: Throwable): CharacterDetailViewState
}

sealed interface CharacterDetailViewIntent: ViewIntent {
    class LoadData(val id: Int): CharacterDetailViewIntent
}

sealed interface CharacterDetailSideEffect: SideEffect {
}