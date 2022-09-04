package com.github.ryjen.jokeapp.domain.arch.redux

// how state is modified in the store
fun interface ReduxReducer<State : ReduxState, in Action : ReduxAction> {
    fun reduce(state: State, action: Action): State
    
    operator fun invoke(state: State, action: Action) = reduce(state, action)
}

// combines reducers if needed. limited to the same type
fun <S : ReduxState, A : ReduxAction> combineReducers(vararg values: ReduxReducer<S, A>) =
    ReduxReducer<S, A> { state, action ->
        values.fold(state) { next, reducer ->
            reducer.reduce(next, action)
        }
    }
