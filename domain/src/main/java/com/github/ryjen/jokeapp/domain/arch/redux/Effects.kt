package com.github.ryjen.jokeapp.domain.arch.redux

// a thunk is a delayed sub routine in the redux store
typealias ReduxThunk<State, Action> = (State, Action, ReduxDispatcher<Action>) -> Unit

// an effect is a delayed sub routine in the redux store
fun interface ReduxEffect<State, Action> {
    fun apply(state: State, action: Action, dispatcher: ReduxDispatcher<Action>)
}

