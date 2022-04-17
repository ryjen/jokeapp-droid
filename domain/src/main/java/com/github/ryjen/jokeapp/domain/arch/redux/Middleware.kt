package com.github.ryjen.jokeapp.domain.arch.redux

// middleware applies thunks to the store
typealias ReduxMiddleware<State, Action> = (State, Action, ReduxDispatcher<Action>) -> Unit

// a thunk is a delayed sub routine in the middleware
interface ReduxThunk<State, Action> {
    suspend fun applyMiddleware(
        state: State,
        action: Action,
        dispatch: ReduxDispatcher<Action>
    )
}

