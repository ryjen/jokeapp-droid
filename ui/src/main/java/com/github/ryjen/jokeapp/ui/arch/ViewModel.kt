package com.github.ryjen.jokeapp.ui.arch

import com.micrantha.kredux.ReduxAction
import com.micrantha.kredux.ReduxDispatcher
import com.micrantha.kredux.coroutines.state.ReduxFlowState
import com.micrantha.kredux.store.ReduxStatefulStore
import kotlinx.coroutines.flow.StateFlow

interface ReduxViewModel<State, VState, Action : ReduxAction> :
    ReduxDispatcher {

    val store: ReduxStatefulStore<State, ReduxFlowState<State>, ReduxFlowState<VState>>

    fun state(): StateFlow<VState> =
        store.state()

    override fun dispatch(action: ReduxAction) {
        store.dispatch(action)
    }
}

