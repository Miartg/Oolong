package com.miartg.oolong.home.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.miartg.oolong.common.ui.feature
import com.miartg.oolong.common.ui.subscribeAsState
import com.miartg.oolong.home.presentation.Home.Counter
import com.miartg.oolong.home.presentation.Home.Counter.onMinusClick
import com.miartg.oolong.home.presentation.Home.Counter.onPlusClick


@Composable
fun HomeScreen() {
    val counterFeature = feature(Counter.State(15))
    val counterReducer = counterFeature.reducer
    val counterState by counterFeature.subscribeAsState()
    Counter(
        count = counterState.count,
        onPlusClick = counterReducer.onPlusClick(),
        onMinusClick = counterReducer.onMinusClick()
    )
}

@Composable
fun Counter(
    count: Int,
    onPlusClick: (count: Int) -> Unit,
    onMinusClick: (count: Int) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = count.toString(), style = MaterialTheme.typography.displayMedium)
        Spacer(modifier = Modifier.size(32.dp))
        Row {
            Button(onClick = { onMinusClick(1) }) {
                Text(text = "- 1")
            }
            Spacer(modifier = Modifier.size(16.dp))
            Button(onClick = { onPlusClick(1) }) {
                Text(text = "+ 1")
            }
        }
        Spacer(modifier = Modifier.size(8.dp))
        Row {
            Button(onClick = { onMinusClick(5) }) {
                Text(text = "- 5")
            }
            Spacer(modifier = Modifier.size(16.dp))
            Button(onClick = { onPlusClick(5) }) {
                Text(text = "+ 5")
            }
        }
    }
}

