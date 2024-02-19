package com.mayank.presentation.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface MVI<UiState, UiAction, SideEffect> {
    val uiState: StateFlow<UiState>
    val sideEffect: SharedFlow<SideEffect>

    fun onAction(uiAction: UiAction)

    fun updateUiState(block: UiState.() -> UiState)

    fun updateUiState(newUiState: UiState)

    fun CoroutineScope.emitSideEffect(effect: SideEffect)
}
