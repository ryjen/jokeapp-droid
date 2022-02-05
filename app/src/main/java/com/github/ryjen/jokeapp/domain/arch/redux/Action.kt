package com.github.ryjen.jokeapp.domain.arch.redux

import androidx.annotation.StringRes
import com.github.ryjen.jokeapp.domain.model.Failure

interface ReduxAction

data class ErrorAction(val error: Failure) : ReduxAction

interface ErrorActionCompanion {
    fun Error(message: String) = ErrorAction(Failure.Message(message))
    fun Error(exception: Throwable) = ErrorAction(Failure.Error(exception))
    fun Error(@StringRes message: Int) = ErrorAction(Failure.Resource(message))
}

interface ReduxActionHandler<T> where T: ReduxAction {
    fun onAction(action: T)
}
