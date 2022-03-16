package com.github.ryjen.jokeapp.domain.arch.redux

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

// the main store
class ReduxStore<S, A>(
    initialState: S,
    private val reducer: ReduxReducer<S, A>,
    private val middleware: ReduxMiddleware<S, A>,
) : ReduxDispatcher<A> where S : ReduxState, A : ReduxAction {
    private val state = MutableStateFlow(initialState)
    
    override fun dispatch(action: A) {
        state.update {
            reducer(it, action)
        }

        middleware(state.value, action, this)
    }

    // flow for compose
    fun stateAsStateFlow() = state.asStateFlow()
}
