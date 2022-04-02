package com.miartg.oolong.counter.presentation

import com.miartg.oolong.common.tea.EffectHandler
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

    fun Reducer.onPlusWithDelayClick() = { count: Int ->
        sideEffect(CounterEff.PlusWithDelay(count, 10L)) { receivedCount: Int ->
            plus(receivedCount)
        }
    }

    fun Reducer.onMinusWithDelayClick() = { count: Int ->
        sideEffect(CounterEff.MinusWithDelay(count, 10L)) { receivedCount: Int ->
            minus(receivedCount)
        }
    }

}

private typealias Reducer = BaseReducer<Counter.State>



// todo use functions instead data class
sealed class CounterEff {
    data class MinusWithDelay(val count: Int, val delay: Long) : CounterEff()
    data class PlusWithDelay(val count: Int, val delay: Long) : CounterEff()
}

// todo impl and register EffectHandler
class CounterEffectHandler(
    private val someDeps: String
) : EffectHandler<CounterEff, Int> {

    override fun setListener(listener: (Int) -> Unit) {

    }

    override fun handleEffect(eff: CounterEff) {

    }

    override fun dispose() {

    }
}