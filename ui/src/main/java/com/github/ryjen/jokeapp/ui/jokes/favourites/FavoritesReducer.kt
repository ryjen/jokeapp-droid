package com.github.ryjen.jokeapp.ui.jokes.favourites

import com.github.ryjen.jokeapp.domain.arch.redux.ReduxReducer

object FavoritesReducer : ReduxReducer<FavoritesState, FavoritesAction> {
    override fun invoke(state: FavoritesState, action: FavoritesAction): FavoritesState =
        when (action) {
            is FavoritesAction.Update -> state.copy(jokes = action.data)
            is FavoritesAction.Remove -> state.copy(
                jokes = state.jokes.minus(action.data)
            )
            is FavoritesAction.Error -> state.copy(error = action.data)
            is FavoritesAction.Init -> state
        }
}
