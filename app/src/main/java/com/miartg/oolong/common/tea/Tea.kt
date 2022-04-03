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

inline fun <S> Reducer<S>.reduce(
    crossinline action: (S) -> S
): () -> Unit = {
    update(action(state))
}

inline fun <S, P1> Reducer<S>.reduce(
    crossinline action: (P1) -> S
): (P1) -> Unit = { p1 ->
    update(action(p1))
}

inline fun <S, P1> Reducer<S>.reduce(
    crossinline action: (S, P1) -> S
): (P1) -> Unit = { p1 ->
    update(action(state, p1))
}

inline fun <S, P1, P2> Reducer<S>.reduce(
    crossinline action: (S, P1, P2) -> S
): (P1, P2) -> Unit = { p1, p2 ->
    update(action(state, p1, p2))
}

inline fun <S, P1, P2, P3> Reducer<S>.reduce(
    crossinline action: (S, P1, P2, P3) -> S
): (P1, P2, P3) -> Unit = { p1, p2, p3 ->
    update(action(state, p1, p2, p3))
}

inline fun <S, P1, P2, P3, P4> Reducer<S>.reduce(
    crossinline action: (S, P1, P2, P3, P4) -> S
): (P1, P2, P3, P4) -> Unit = { p1, p2, p3, p4 ->
    update(action(state, p1, p2, p3, p4))
}
