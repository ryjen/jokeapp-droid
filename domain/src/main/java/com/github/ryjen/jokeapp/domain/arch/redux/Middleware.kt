package com.github.ryjen.jokeapp.domain.arch.redux

// a thunk is a delayed sub routine in the redux store
typealias ReduxThunk<State, Action> = (State, Action, ReduxDispatcher<Action>) -> Unit

interface ReduxMiddleware<State, Action> {
    suspend fun applyMiddleware(
        state: State,
        action: Action,
        dispatch: ReduxDispatcher<Action>
    )
}

