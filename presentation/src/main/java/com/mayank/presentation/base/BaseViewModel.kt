package com.mayank.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

abstract class BaseViewModel<VS: ViewState, VI: ViewIntent, SE: SideEffect>: ViewModel() {

    protected val state = MutableSharedFlow<VS>()

    val stateSharedFlow get() = state.asSharedFlow()

    protected val sideEffect = MutableSharedFlow<SE>()

    val sideEffectSharedFlow get() = sideEffect.asSharedFlow()
    abstract fun sendIntent(intent: VI)

}