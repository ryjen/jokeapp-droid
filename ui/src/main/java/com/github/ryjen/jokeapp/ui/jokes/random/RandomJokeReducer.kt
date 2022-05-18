package com.github.ryjen.jokeapp.ui.jokes.random

import com.github.ryjen.jokeapp.domain.arch.redux.ReduxReducer

object RandomJokeReducer : ReduxReducer<RandomJokeState, RandomJokeAction> {
    override fun invoke(state: RandomJokeState, action: RandomJokeAction) =
        when (action) {
            is RandomJokeAction.Init -> state.copy(
                joke = null
            )
            is RandomJokeAction.Refresh -> state.copy(
                joke = action.data
            )
            is RandomJokeAction.Error -> state.copy(
                error = action.data
            )
            is RandomJokeAction.UnFavorite -> state.copy(
                joke = action.data.copy(isFavorite = false)
            )
            is RandomJokeAction.Favorite -> state.copy(
                joke = action.data.copy(isFavorite = true)
            )
            else -> state
        }
}
