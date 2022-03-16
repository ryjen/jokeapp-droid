package com.github.ryjen.jokeapp.domain.arch.redux

// how the redux store dispatches actions
interface ReduxDispatcher<Action> {
    fun dispatch(action: Action)
}
