package com.mayank.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<VS: ViewState, VI: ViewIntent, SE: SideEffect>: ViewModel() {

    private val initialState : VS by lazy {createInitialState()}

    protected val state = MutableStateFlow(initialState)

    val stateFlow: StateFlow<VS>
        get() = state

    protected val sideEffect = MutableSharedFlow<SE>()

    val sideEffectSharedFlow: SharedFlow<SE> get() = sideEffect
    abstract fun sendIntent(intent: VI)

    abstract fun createInitialState() : VS

}