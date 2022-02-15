package com.github.ryjen.jokeapp.ui.arch.redux

import androidx.annotation.StringRes
import com.github.ryjen.jokeapp.domain.arch.redux.ReduxAction
import com.github.ryjen.jokeapp.ui.arch.Failure

data class ReduxActionWithError(val error: Failure) : ReduxAction

interface ReduxActionWithErrorCompanion {
    fun Error(message: String) = ReduxActionWithError(Failure.Message(message))
    fun Error(exception: Throwable) = ReduxActionWithError(Failure.Error(exception))
    fun Error(@StringRes message: Int) = ReduxActionWithError(Failure.Resource(message))
}
