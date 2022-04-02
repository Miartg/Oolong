package com.miartg.oolong.common.ui.tea

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.miartg.oolong.common.tea.Feature

@Composable
fun <S> Feature<S>.subscribeAsState(): State<S> = stateFlow.collectAsState()

@Composable
fun <S> feature(initialState: S) = feature { Feature(initialState) }

@Composable
operator fun <S> Feature<S>.component1() = subscribeAsState().value

operator fun <S> Feature<S>.component2() = reducer

@Suppress("UNCHECKED_CAST")
@Composable
fun <S> feature(
    key: String? = null,
    factory: () -> Feature<S>,
) =
    viewModel(
        FeatureViewModel::class.java,
        key = key ?: factory.toString(),
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(FeatureViewModel::class.java)) {
                    return FeatureViewModel(factory()) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    ).feature as Feature<S>

private class FeatureViewModel<S>(
    val feature: Feature<S>,
) : ViewModel()

