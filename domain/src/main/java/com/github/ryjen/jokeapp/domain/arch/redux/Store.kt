package com.github.ryjen.jokeapp.domain.arch.redux

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

// the main store
class ReduxStore<S, A>(
    initialState: S,
) : ReduxDispatcher<A> where S : ReduxState, A : ReduxAction {
    private val state = MutableStateFlow(initialState)

    private val reducers = mutableListOf<ReduxReducer<S, A>>()
    private val middlewares = mutableListOf<ReduxMiddleware<S, A>>()

    fun addReducer(reducer: ReduxReducer<S, A>): ReduxStore<S, A> {
        reducers.add(reducer)
        return this
    }

    operator fun plus(reducer: ReduxReducer<S, A>) = addReducer(reducer)

    fun addMiddleware(middleware: ReduxMiddleware<S, A>): ReduxStore<S, A> {
        middlewares.add(middleware)
        return this
    }

    operator fun plus(middleware: ReduxMiddleware<S, A>) = addMiddleware(middleware)

    override fun dispatch(action: A) {
        state.update {
            reducers.fold(it) { next, reducer -> reducer(next, action) }
        }

        middlewares.forEach { middleware ->
            middleware(state.value, action, this)
        }
    }

    // flow for compose
    fun stateAsStateFlow() = state.asStateFlow()
}
