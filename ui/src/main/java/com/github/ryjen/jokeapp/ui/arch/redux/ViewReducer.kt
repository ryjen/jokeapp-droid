package com.github.ryjen.jokeapp.ui.arch.redux

import com.github.ryjen.jokeapp.domain.arch.redux.ReduxAction
import com.github.ryjen.jokeapp.domain.arch.redux.ReduxReducer
import com.github.ryjen.jokeapp.domain.arch.redux.ReduxState
import com.github.ryjen.jokeapp.ui.arch.ViewState

interface ViewReducer<State : ReduxState, VState : ViewState, Action : ReduxAction> :
    ReduxReducer<State, Action> {

    override fun apply(state: State, action: Action): State {
        return reduce(state, action)
    }

    fun reduce(state: State, action: Action): State
}
