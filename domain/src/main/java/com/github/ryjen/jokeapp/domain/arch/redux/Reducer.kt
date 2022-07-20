package com.github.ryjen.jokeapp.domain.arch.redux

// how state is modified in the store
fun interface ReduxReducer<State, Action> {
    fun apply(state: State, action: Action): State
}

// combines reducers if needed. limited to the same type
fun <S : ReduxState, A : ReduxAction> combineReducers(vararg values: ReduxReducer<S, A>) =
    ReduxReducer<S, A> { state, action ->
        values.fold(state) { next, reducer ->
            reducer.apply(next, action)
        }
    }
