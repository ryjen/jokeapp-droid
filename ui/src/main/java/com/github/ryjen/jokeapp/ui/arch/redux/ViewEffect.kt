package com.github.ryjen.jokeapp.ui.arch.redux

import com.github.ryjen.jokeapp.domain.arch.redux.ReduxAction
import com.github.ryjen.jokeapp.domain.arch.redux.ReduxDispatcher
import com.github.ryjen.jokeapp.domain.arch.redux.ReduxEffect
import com.github.ryjen.jokeapp.domain.arch.redux.ReduxState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class ViewEffect<State : ReduxState, Action : ReduxAction>(
    private val scope: CoroutineScope
) : ReduxEffect<State, Action> {

    override fun apply(state: State, action: Action, dispatcher: ReduxDispatcher<Action>) {
        scope.launch {
            effects(state, action, dispatcher)
        }
    }

    abstract suspend fun effects(state: State, action: Action, dispatcher: ReduxDispatcher<Action>)
}
