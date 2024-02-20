package com.mayank.presentation.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface MVI<ViewState, ViewIntent, SideEffect> {
    val viewState: StateFlow<ViewState>
    val sideEffect: SharedFlow<SideEffect>

    fun sendIntent(intent: ViewIntent)

    fun updateViewState(block: ViewState.() -> ViewState)

    fun updateViewState(newViewState: ViewState)

    fun CoroutineScope.emitSideEffect(effect: SideEffect)
}
