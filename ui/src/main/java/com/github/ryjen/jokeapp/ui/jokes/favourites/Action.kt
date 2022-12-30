package com.github.ryjen.jokeapp.ui.jokes.favourites

import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.ui.arch.Failure
import com.micrantha.kredux.ReduxAction

sealed class FavoritesAction : ReduxAction {
    data class Update(val data: List<Joke>) : FavoritesAction()
    data class Remove(val data: Joke) : FavoritesAction()
    data class Error(val data: Failure) : FavoritesAction()
    object Init : FavoritesAction()

    companion object {
        fun Error(throwable: Throwable) = Error(Failure.Error(throwable))
    }
}
