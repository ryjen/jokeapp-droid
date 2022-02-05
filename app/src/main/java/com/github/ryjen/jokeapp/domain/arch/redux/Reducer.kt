package com.github.ryjen.jokeapp.domain.arch.redux

typealias ReduxReducer<State, Action> = (State, Action) -> State

fun <S> combineReducers(vararg values: ReduxReducer<S,*>):
        ReduxReducer<S,ReduxAction> where S: ReduxState = { state, action ->

    val reducers = values.filterIsInstance<ReduxReducer<S, ReduxAction>>()

    reducers.fold(state) { next, reducer ->
        reducer(next, action)
    }
}

typealias ErrorReducer<State> = ReduxReducer<State, ErrorAction>
