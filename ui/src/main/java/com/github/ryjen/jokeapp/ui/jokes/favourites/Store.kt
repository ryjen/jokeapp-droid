package com.github.ryjen.jokeapp.ui.jokes.favourites

import com.github.ryjen.jokeapp.domain.arch.redux.ScopedReduxFlowStore
import kotlinx.coroutines.CoroutineScope

class FavoritesStore(
    override val reduxScope: CoroutineScope,
) : ScopedReduxFlowStore<FavoritesState, FavoritesAction>(
    reduxScope, FavoritesState()
) {
    init {
        // store reduces the state
        addReducer(::favoritesReducer)
    }
}
