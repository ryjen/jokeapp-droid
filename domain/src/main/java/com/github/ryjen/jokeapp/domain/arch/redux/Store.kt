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
    private val middlewares = mutableListOf<ReduxThunk<S, A>>()

    fun addReducer(reducer: ReduxReducer<S, A>): ReduxStore<S, A> {
        reducers.add(reducer)
        return this
    }

    operator fun plus(reducer: ReduxReducer<S, A>) = addReducer(reducer)

    fun addMiddleware(thunk: ReduxThunk<S, A>): ReduxStore<S, A> {
        middlewares.add(thunk)
        return this
    }

    operator fun plus(thunk: ReduxThunk<S, A>) = addMiddleware(thunk)

    override fun dispatch(action: A) {
        state.update {
            reducers.fold(it) { next, reducer -> reducer(next, action) }
        }

        middlewares.forEach { thunk ->
            thunk(state.value, action, this)
        }
    }

    // flow for compose
    fun stateAsStateFlow() = state.asStateFlow()
}
