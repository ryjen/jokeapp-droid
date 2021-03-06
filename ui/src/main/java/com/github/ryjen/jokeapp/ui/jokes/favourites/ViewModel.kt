package com.github.ryjen.jokeapp.ui.jokes.favourites

import androidx.lifecycle.viewModelScope
import com.github.ryjen.jokeapp.domain.arch.redux.ReduxStore
import com.github.ryjen.jokeapp.domain.facades.JokeFacade
import com.github.ryjen.jokeapp.ui.arch.ReduxViewModel

class FavoritesViewModel(
    facade: JokeFacade
) : ReduxViewModel<FavoritesState, FavoritesViewState, FavoritesAction>() {

    override val store =
        ReduxStore<FavoritesState, FavoritesAction>(FavoritesState()) + ::favoritesReducer + FavoritesEffects(
            viewModelScope, facade, this
        )

    init {
        dispatch(FavoritesAction.Init)
    }

    override fun mapViewState(state: FavoritesState) = FavoritesViewState(
        jokes = state.jokes
    )
}
