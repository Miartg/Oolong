package com.miartg.oolong.counter.presentation

import com.miartg.oolong.common.tea.reduce
import com.miartg.oolong.common.tea.Reducer as BaseReducer

object Counter3 {

    data class State(val count: Int)

    fun Reducer3.onMinusClick() = reduce(::minus)
    fun Reducer3.onPlusClick() = reduce(::plus)

    private fun minus(state: State, count: Int): State =
        updateCount(state, state.count - count)

    private fun plus(state: State, count: Int): State =
        updateCount(state, state.count + count)

    private fun updateCount(state: State, count: Int): State =
        if (count in 0..30) state.copy(count = count) else state

}

private typealias Reducer3 = BaseReducer<Counter3.State>