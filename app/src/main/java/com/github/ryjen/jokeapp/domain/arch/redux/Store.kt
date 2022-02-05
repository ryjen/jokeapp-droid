package com.github.ryjen.jokeapp.domain.arch.redux

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ReduxStore<S>(
    initialState: S,
    private val reducer: ReduxReducer<S, ReduxAction>,
) where S : ReduxState {
    private val state = MutableStateFlow(initialState)

    val currentState get() = state.value

    fun dispatch(action: ReduxAction) {
        state.update { reducer(it, action) }
    }

    fun stateAsStateFlow(): StateFlow<S> {
        return state.asStateFlow()
    }
}
