package com.github.ryjen.jokeapp.ui.jokes.favourites

import com.github.ryjen.jokeapp.domain.arch.redux.FlowReduxStore
import kotlinx.coroutines.CoroutineScope

class FavoritesStore(
    override val reduxScope: CoroutineScope,
) :
    FlowReduxStore<FavoritesState, FavoritesAction>(
        FavoritesState(), reduxScope
    ) {

    init {
        addReducer(::favoritesReducer)
    }
}
