package com.miartg.oolong.counter.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miartg.oolong.common.ui.tea.component1
import com.miartg.oolong.common.ui.tea.component2
import com.miartg.oolong.common.ui.tea.feature
import com.miartg.oolong.counter.presentation.Counter
import com.miartg.oolong.counter.presentation.Counter.onMinusClick
import com.miartg.oolong.counter.presentation.Counter.onPlusClick
import com.miartg.oolong.counter.presentation.Counter2
import com.miartg.oolong.counter.presentation.Counter3
import com.miartg.oolong.counter.presentation.Counter3.onMinusClick
import com.miartg.oolong.counter.presentation.Counter3.onPlusClick
import com.miartg.oolong.counter.presentation.accept


@Composable
fun CounterScreen() {
    val (state, reducer) = feature(Counter.State(15))
    Counter(
        count = state.count,
        onPlusClick = reducer.onPlusClick(),
        onMinusClick = reducer.onMinusClick(),
    )
}

@Composable
fun CounterScreen2() {
    val (state, feature) = feature(Counter2.State(15))
    Counter(
        count = state.count,
        onPlusClick = { count -> feature.accept(Counter2.Msg.OnPlusClick(count)) },
        onMinusClick = { count -> feature.accept(Counter2.Msg.OnMinusClick(count)) },
    )
}

@Composable
fun CounterScreen3() {
    val (state, reducer) = feature(Counter3.State(15))
    Counter(
        count = state.count,
        onPlusClick = reducer.onPlusClick(),
        onMinusClick = reducer.onMinusClick(),
    )
}

@Composable
fun Counter(
    count: Int,
    onPlusClick: (count: Int) -> Unit,
    onMinusClick: (count: Int) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.width(IntrinsicSize.Max),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = count.toString(), style = MaterialTheme.typography.displayMedium)
            Spacer(modifier = Modifier.size(32.dp))
            Row {
                Button(onClick = { onMinusClick(1) }) {
                    Text(text = "- 1")
                }
                Spacer(modifier = Modifier.size(8.dp))
                Button(onClick = { onPlusClick(1) }) {
                    Text(text = "+ 1")
                }
            }
            Spacer(modifier = Modifier.size(8.dp))
            Row {
                Button(onClick = { onMinusClick(5) }) {
                    Text(text = "- 5")
                }
                Spacer(modifier = Modifier.size(8.dp))
                Button(onClick = { onPlusClick(5) }) {
                    Text(text = "+ 5")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CounterPreview() {
    Counter(
        count = 15,
        onPlusClick = {},
        onMinusClick = {},
    )
}
