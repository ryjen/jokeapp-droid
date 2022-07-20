package com.github.ryjen.jokeapp.ui.jokes.random

import com.github.ryjen.jokeapp.ui.arch.redux.ViewReducer

object RandomJokeReducer : ViewReducer<RandomJokeState, RandomJokeViewState, RandomJokeAction> {
    override fun reduce(state: RandomJokeState, action: RandomJokeAction) =
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
