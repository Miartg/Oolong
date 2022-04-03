package com.miartg.oolong.counter.presentation

import com.miartg.oolong.common.tea.Reducer

object Counter2 {

    data class State(val count: Int)

    sealed class Msg {
        data class OnMinusClick(val count: Int) : Msg()
        data class OnPlusClick(val count: Int) : Msg()
    }

    fun reduce(state: State, msg: Msg): State = when (msg) {
        is Msg.OnMinusClick -> minus(state, msg.count)
        is Msg.OnPlusClick -> plus(state, msg.count)
    }

    private fun minus(state: State, count: Int): State =
        updateCount(state, state.count - count)

    private fun plus(state: State, count: Int): State =
        updateCount(state, state.count + count)

    private fun updateCount(state: State, count: Int): State =
        if (count in 0..30) state.copy(count = count) else state

}

fun Reducer<Counter2.State>.accept(msg: Counter2.Msg) {
    update(Counter2.reduce(state, msg))
}


