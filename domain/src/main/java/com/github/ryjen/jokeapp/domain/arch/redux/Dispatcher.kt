package com.github.ryjen.jokeapp.domain.arch.redux

// how the redux store dispatches actions
fun interface ReduxDispatcher<in Action : ReduxAction> {
    fun dispatch(action: Action)
}

typealias Dispatch<Action> = (Action) -> Unit
