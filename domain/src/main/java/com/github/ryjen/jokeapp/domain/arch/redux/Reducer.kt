package com.github.ryjen.jokeapp.domain.arch.redux

// how state is modified in the store
typealias ReduxReducer<State, Action> = (State, Action) -> State

// combines reducers if needed. limited to the same type
fun <S : ReduxState, A : ReduxAction> combineReducers(vararg values: ReduxReducer<S, A>):
        ReduxReducer<S, A> = { state, action ->
    values.fold(state) { next, reducer ->
        reducer(next, action)
    }
}
