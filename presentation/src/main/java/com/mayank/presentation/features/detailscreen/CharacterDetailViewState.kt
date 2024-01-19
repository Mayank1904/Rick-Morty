package com.mayank.presentation.features.detailscreen

import com.mayank.presentation.base.SideEffect
import com.mayank.presentation.base.ViewIntent
import com.mayank.presentation.base.ViewState
import com.mayank.presentation.models.CharacterItem

sealed interface CharacterDetailViewState : ViewState {
    object Loading : CharacterDetailViewState

    data class Success(val data: CharacterItem) : CharacterDetailViewState

    data class Error(private val throwable: Throwable) : CharacterDetailViewState
}

interface CharacterDetailViewIntent : ViewIntent {
    class LoadData(val id: Int) : CharacterDetailViewIntent
}

interface CharacterDetailSideEffect : SideEffect