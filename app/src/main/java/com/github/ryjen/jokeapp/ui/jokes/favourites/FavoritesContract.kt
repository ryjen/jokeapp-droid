package com.github.ryjen.jokeapp.ui.jokes.favourites

import com.github.ryjen.jokeapp.domain.arch.redux.ReduxAction
import com.github.ryjen.jokeapp.domain.arch.redux.ReduxState
import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.ui.arch.Failure

data class FavoritesState(
    val jokes: List<Joke> = listOf(),
    val error: Failure? = null
) : ReduxState

sealed class FavoritesActions : ReduxAction {
    data class Init(val data: List<Joke>) : FavoritesActions()
    data class Add(val data: Joke) : FavoritesActions()
    data class Remove(val data: Joke) : FavoritesActions()
    data class Error(val data: Failure) : FavoritesActions()

    companion object {
        fun Error(throwable: Throwable) = Error(Failure.Error(throwable))
    }
}
