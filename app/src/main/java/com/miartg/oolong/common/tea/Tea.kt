package com.miartg.oolong.common.tea

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class Feature<S>(initialState: S) {
    private val flowReducer = FlowReducer(initialState)
    val reducer: Reducer<S> = flowReducer
    val stateFlow: StateFlow<S> = flowReducer.stateFlow
}

interface Reducer<S> {
    val state: S
    fun update(state: S)
    fun <Eff : Any, Result : Any> sideEffect(eff: Eff, onResult: (Result) -> Unit)
}

private class FlowReducer<S>(initialState: S) : Reducer<S> {

    private val mutableStateFlow = MutableStateFlow(initialState)
    val stateFlow: StateFlow<S> = mutableStateFlow

    override val state: S
        get() = mutableStateFlow.value

    override fun update(state: S) {
        mutableStateFlow.update { state }
    }

    override fun <Eff : Any, Result : Any> sideEffect(eff: Eff, onResult: (Result) -> Unit) {
        // todo run side effect and call on result
    }
}

// todo use functions instead Eff type
interface EffectHandler<Eff : Any, Result : Any> : Disposable {
    fun setListener(listener: (Result) -> Unit)
    fun handleEffect(eff: Eff)
}

interface Disposable {
    fun dispose()
}



