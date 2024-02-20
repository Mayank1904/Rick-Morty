package com.mayank.presentation.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MVIDelegate<ViewState, ViewIntent, SideEffect> internal constructor(
    initialUiState: ViewState,
) : MVI<ViewState, ViewIntent, SideEffect> {

    private val _viewState = MutableStateFlow(initialUiState)
    override val viewState: StateFlow<ViewState> = _viewState.asStateFlow()

    private val _sideEffect by lazy { MutableSharedFlow<SideEffect>() }
    override val sideEffect: SharedFlow<SideEffect> by lazy { _sideEffect.asSharedFlow() }

    override fun sendIntent(intent: ViewIntent) {}

    override fun updateViewState(newViewState: ViewState) {
        _viewState.update { newViewState }
    }

    override fun updateViewState(block: ViewState.() -> ViewState) {
        _viewState.update(block)
    }

    override fun CoroutineScope.emitSideEffect(effect: SideEffect) {
        this.launch { _sideEffect.emit(effect) }
    }
}

fun <ViewState, ViewIntent, SideEffect> mvi(
    initialUiState: ViewState,
): MVI<ViewState, ViewIntent, SideEffect> = MVIDelegate(initialUiState)
