package com.github.ryjen.jokeapp.ui.jokes.favourites

fun favoritesReducer(state: FavoritesState, action: FavoritesAction) =
    when (action) {
        is FavoritesAction.Update -> state.copy(jokes = action.data)
        is FavoritesAction.Remove -> state.copy(
            jokes = state.jokes.minus(action.data)
        )
        is FavoritesAction.Error -> state.copy(error = action.data)
        is FavoritesAction.Init -> state
    }

