package com.github.ryjen.jokeapp.domain.arch.redux

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// a thunk is a delayed sub routine in the redux store
typealias ReduxThunk<State, Action> = (State, Action, ReduxDispatcher<Action>) -> Unit

// an effect is a delayed sub routine in the redux store
fun interface ReduxEffect<State, Action> {
    fun apply(state: State, action: Action, dispatcher: ReduxDispatcher<Action>)
}

// run effects in scope
abstract class ScopedReduxEffect<State : ReduxState, Action : ReduxAction>(
    private val scope: CoroutineScope
) : ReduxEffect<State, Action> {

    override fun apply(state: State, action: Action, dispatcher: ReduxDispatcher<Action>) {
        scope.launch {
            effects(state, action, dispatcher)
        }
    }

    abstract suspend fun effects(state: State, action: Action, dispatcher: ReduxDispatcher<Action>)
}
