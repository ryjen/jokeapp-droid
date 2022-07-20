package com.github.ryjen.jokeapp.domain.arch.redux

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ReduxStore<S, A>(
    initialState: S,
) : ReduxDispatcher<A> where S : ReduxState, A : ReduxAction {
    private val state = MutableStateFlow(initialState)

    private val reducers = mutableListOf<ReduxReducer<S, A>>()
    private val effects = mutableListOf<ReduxEffect<S, A>>()

    fun addReducer(reducer: ReduxReducer<S, A>): ReduxStore<S, A> {
        reducers.add(reducer)
        return this
    }

    operator fun plus(reducer: ReduxReducer<S, A>) = addReducer(reducer)

    fun addEffect(effect: ReduxEffect<S, A>): ReduxStore<S, A> {
        effects.add(effect)
        return this
    }

    operator fun plus(effect: ReduxEffect<S, A>) = addEffect(effect)

    override fun dispatch(action: A) {
        state.update {
            reducers.fold(it) { next, reducer -> reducer.apply(next, action) }
        }

        effects.forEach { effect ->
            effect.apply(state.value, action, this)
        }
    }

    fun stateAsStateFlow() = state.asStateFlow()
}
