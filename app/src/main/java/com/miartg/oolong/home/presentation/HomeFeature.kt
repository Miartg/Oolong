package com.miartg.oolong.home.presentation

import com.miartg.oolong.common.tea.Reducer as BaseReducer

object Home {

    data class State(
        val count: Int = 0
    )

    fun Reducer.onMinusClick() = { count: Int ->
        if (state.count - count >= 0) {
            update(state.copy(count = state.count - count))
        }
    }

    fun Reducer.onPlusClick() = { count: Int ->
        if (state.count + count <= 30) {
            update(state.copy(count = state.count + count))
        }
    }

}

private typealias Reducer = BaseReducer<Home.State>