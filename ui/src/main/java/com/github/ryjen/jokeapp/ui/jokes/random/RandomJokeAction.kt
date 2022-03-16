package com.github.ryjen.jokeapp.ui.jokes.random

import com.github.ryjen.jokeapp.domain.arch.redux.ReduxAction
import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.ui.arch.Failure

sealed class RandomJokeAction : ReduxAction {
    data class Refresh(val data: Joke? = null) : RandomJokeAction()
    object RefreshClick : RandomJokeAction()
    data class Error(val data: Failure) : RandomJokeAction()
    data class UnFavorite(val data: Joke) : RandomJokeAction()
    data class Favorite(val data: Joke) : RandomJokeAction()
    object Init : RandomJokeAction()

    companion object {
        fun Error(throwable: Throwable) =
            Error(Failure.Error(throwable))
    }
}
