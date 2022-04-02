package com.miartg.oolong.home.presentation

import com.miartg.oolong.common.tea.Reducer

object Home {

    object Counter {
        data class State(
            val count: Int = 0
        )

        fun Reducer<State>.onMinusClick() = { count: Int ->
            if (state.count - count >= 0) {
                update(state.copy(count = state.count - count))
            }
        }

        fun Reducer<State>.onPlusClick() = { count: Int ->
            if (state.count + count <= 30) {
                update(state.copy(count = state.count + count))
            }
        }
    }



}


