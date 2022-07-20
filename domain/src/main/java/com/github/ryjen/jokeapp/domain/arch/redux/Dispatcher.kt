package com.github.ryjen.jokeapp.domain.arch.redux

// how the redux store dispatches actions
fun interface ReduxDispatcher<Action> {
    fun dispatch(action: Action)
}
