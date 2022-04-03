package com.miartg.oolong.counter.presentation

import com.miartg.oolong.common.tea.Reducer as BaseReducer

object Counter {

    data class State(val count: Int)

    fun Reducer.onMinusClick() = { count: Int ->
        minus(count)
    }

    fun Reducer.onPlusClick() = { count: Int ->
        plus(count)
    }

    private fun Reducer.minus(count: Int) {
        updateCount(state.count - count)
    }

    private fun Reducer.plus(count: Int) {
        updateCount(state.count + count)
    }

    private fun Reducer.updateCount(count: Int) {
        if (count in 0..30) update(state.copy(count = count))
    }

}

private typealias Reducer = BaseReducer<Counter.State>