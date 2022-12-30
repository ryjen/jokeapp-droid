package com.github.ryjen.jokeapp.ui.jokes.favourites

import com.github.ryjen.jokeapp.domain.arch.redux.ScopedReduxFlowStore
import com.micrantha.kredux.store.mappedStoreOf
import kotlinx.coroutines.CoroutineScope


class FavoritesStore(
    private val store = mappedStoreOf(initialState = FavoritesStates()) {
        FavoritesViewState(it)
    }
) : ScopedReduxFlowStore<FavoritesState, FavoritesAction>(
    reduxScope, FavoritesState()
) {
    override val reduxScope: CoroutineScope

    init {
        // store reduces the state
        addReducer(::favoritesReducer)
    }
}
