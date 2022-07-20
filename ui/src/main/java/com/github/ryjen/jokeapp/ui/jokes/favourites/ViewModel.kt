package com.github.ryjen.jokeapp.ui.jokes.favourites

import androidx.lifecycle.viewModelScope
import com.github.ryjen.jokeapp.domain.arch.redux.ReduxStore
import com.github.ryjen.jokeapp.ui.arch.redux.ReduxViewModel

class FavoritesViewModel(
    facade: FavoriteJokesFacade
) : ReduxViewModel<FavoritesState, FavoritesViewState, FavoritesAction>() {

    override val store =
        ReduxStore<FavoritesState, FavoritesAction>(FavoritesState()) + ::favoritesReducer + FavoriteJokesEffects(
            viewModelScope, facade, this
        )

    init {
        dispatch(FavoritesAction.Init)
    }

    override fun mapViewState(state: FavoritesState) = FavoritesViewState(
        jokes = state.jokes
    )
}
