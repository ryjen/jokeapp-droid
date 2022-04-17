package com.github.ryjen.jokeapp.ui.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ryjen.jokeapp.domain.arch.redux.*
import kotlinx.coroutines.launch

abstract class ReduxViewModel<S : ReduxState, A : ReduxAction> : ViewModel(),
    ReduxMiddleware<S, A>,
    ReduxThunk<S, A> {

    protected abstract val store: ReduxStore<S, A>

    internal val state by lazy {
        store.stateAsStateFlow()
    }

    override fun invoke(
        state: S,
        action: A,
        dispatch: ReduxDispatcher<A>
    ) {
        viewModelScope.launch {
            applyMiddleware(state, action, dispatch)
        }
    }

    fun dispatch(action: A) {
        store.dispatch(action)
    }
}
