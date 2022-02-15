package com.github.ryjen.jokeapp.domain.arch.redux

interface ReduxAction

interface ReduxActionHandler<T> where T: ReduxAction {
    fun onAction(action: T)
}
