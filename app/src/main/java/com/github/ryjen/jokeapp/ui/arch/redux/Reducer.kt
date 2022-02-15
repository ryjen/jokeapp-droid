package com.github.ryjen.jokeapp.ui.arch.redux

import com.github.ryjen.jokeapp.domain.arch.redux.ReduxReducer

typealias ReduxReducerWithError<State> = ReduxReducer<State, ReduxActionWithError>
