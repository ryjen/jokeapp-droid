package com.github.ryjen.jokeapp.domain.arch.redux

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ReduxStore<S, A>(
    initialState: S,
    private val reducer: ReduxReducer<S, A>,
) where S : ReduxState, A : ReduxAction {
    private val state = MutableStateFlow(initialState)

    val currentState get() = state.value

    fun dispatch(action: A) = state.update { reducer(it, action) }

    fun stateAsStateFlow() = state.asStateFlow()
}
