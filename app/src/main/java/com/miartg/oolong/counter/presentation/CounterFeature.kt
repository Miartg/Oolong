package com.miartg.oolong.counter.presentation

import com.miartg.oolong.common.tea.Reducer as BaseReducer

object Counter {

    data class State(val count: Int)

    fun Reducer.onMinusClick() = { count: Int ->
        if (state.canMinus(count)) minus(count)
    }

    fun Reducer.onPlusClick() = { count: Int ->
        if (state.canPlus(count)) plus(count)
    }

    private fun State.canMinus(count: Int) =
        this.count - count >= 0

    private fun State.canPlus(count: Int) =
        this.count + count <= 30

    private fun Reducer.minus(count: Int) {
        updateCount(state.count - count)
    }

    private fun Reducer.plus(count: Int) {
        updateCount(state.count + count)
    }

    private fun Reducer.updateCount(count: Int) {
        update(state.copy(count = count))
    }

}

private typealias Reducer = BaseReducer<Counter.State>