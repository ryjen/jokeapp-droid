package com.github.ryjen.jokeapp.ui.jokes.random

import com.github.ryjen.jokeapp.domain.arch.redux.ReduxAction
import com.github.ryjen.jokeapp.domain.arch.redux.ReduxState
import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.ui.arch.Failure

data class JokeState(
    val joke: Joke? = null,
    val error: Failure? = null
) : ReduxState

sealed class JokeActions : ReduxAction {
    data class Refresh(val data: Joke) : JokeActions()
    data class Error(val data: Failure) : JokeActions()
    data class Remove(val data: Joke) : JokeActions()
    data class Add(val data: Joke) : JokeActions()

    companion object {
        fun Error(throwable: Throwable) = Error(Failure.Error(throwable))
    }
}
