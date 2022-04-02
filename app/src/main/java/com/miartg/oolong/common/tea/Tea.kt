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
}

private class FlowReducer<S>(initialState: S) : Reducer<S> {

    private val mutableStateFlow = MutableStateFlow(initialState)
    val stateFlow: StateFlow<S> = mutableStateFlow

    override val state: S
        get() = mutableStateFlow.value

    override fun update(state: S) {
        mutableStateFlow.update { state }
    }

}



