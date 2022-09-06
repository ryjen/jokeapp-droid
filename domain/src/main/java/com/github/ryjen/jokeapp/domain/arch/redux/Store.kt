package com.github.ryjen.jokeapp.domain.arch.redux

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

interface ReduxStore<S : ReduxState, A : ReduxAction> : ReduxDispatcher<A> {

    // scope for the store
    val reduxScope: CoroutineScope

    // add reducers
    fun addReducer(reducer: ReduxReducer<S, A>): ReduxStore<S, A>
    operator fun plus(reducer: ReduxReducer<S, A>) = addReducer(reducer)

    // add effects
    fun addEffect(effect: ReduxEffect<S, A>): ReduxStore<S, A>
    operator fun plus(effect: ReduxEffect<S, A>) = addEffect(effect)
}

open class ScopedReduxFlowStore<S : ReduxState, A : ReduxAction>(
    override val reduxScope: CoroutineScope,
    initialState: S,
) : ReduxStore<S, A> {

    private val state = MutableStateFlow(initialState)

    private val reducers = mutableListOf<ReduxReducer<S, A>>()
    private val effects = mutableListOf<ReduxEffect<S, A>>()

    override fun addReducer(reducer: ReduxReducer<S, A>): ReduxStore<S, A> {
        reducers.add(reducer)
        return this
    }

    override fun addEffect(effect: ReduxEffect<S, A>): ReduxStore<S, A> {
        effects.add(effect)
        return this
    }

    override fun dispatch(action: A) {
        state.update {
            reducers.fold(it) { next, reducer -> reducer.reduce(next, action) }
        }

        effects.forEach { effect ->
            reduxScope.launch {
                effect.applyEffect(state.value, action, ::dispatch)
            }
        }
    }

    fun stateAsStateFlow(): StateFlow<S> = state.asStateFlow()
}
