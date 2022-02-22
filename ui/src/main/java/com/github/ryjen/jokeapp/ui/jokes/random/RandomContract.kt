package com.github.ryjen.jokeapp.ui.jokes.random

import com.github.ryjen.jokeapp.domain.arch.redux.ReduxAction
import com.github.ryjen.jokeapp.domain.arch.redux.ReduxState
import com.github.ryjen.jokeapp.domain.model.Joke

data class JokeState(
    val joke: Joke? = null,
    val error: com.github.ryjen.jokeapp.ui.arch.Failure? = null
) : ReduxState

sealed class JokeActions : ReduxAction {
    data class Refresh(val data: Joke?) : JokeActions()
    data class Error(val data: com.github.ryjen.jokeapp.ui.arch.Failure) : JokeActions()
    data class UnFavorite(val data: Joke) : JokeActions()
    data class Favorite(val data: Joke) : JokeActions()

    companion object {
        fun Error(throwable: Throwable) =
            Error(com.github.ryjen.jokeapp.ui.arch.Failure.Error(throwable))
    }
}
