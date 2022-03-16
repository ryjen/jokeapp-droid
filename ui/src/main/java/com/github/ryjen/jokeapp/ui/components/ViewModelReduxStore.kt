package com.github.ryjen.jokeapp.ui.components

import androidx.lifecycle.ViewModel
import com.github.ryjen.jokeapp.domain.arch.redux.*

abstract class ViewModelReduxStore<S : ReduxState, A : ReduxAction>(
) : ViewModel(), ReduxDispatcher<A> {

    protected abstract val initialState: S

    protected abstract fun reducer(): ReduxReducer<S, A>

    protected abstract fun middleware(): ReduxMiddleware<S, A>

    private val store by lazy {
        ReduxStore(initialState, reducer(), middleware())
    }

    val state by lazy { store.stateAsStateFlow() }

    override fun dispatch(action: A) {
        store.dispatch(action)
    }
}
