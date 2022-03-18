package com.github.ryjen.jokeapp.ui.jokes.favourites

import com.github.ryjen.jokeapp.domain.arch.Outcome
import com.github.ryjen.jokeapp.domain.arch.redux.ReduxDispatcher
import com.github.ryjen.jokeapp.domain.arch.redux.ReduxStore
import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.domain.usecase.GetFavoriteJokes
import com.github.ryjen.jokeapp.domain.usecase.RemoveFavoriteJoke
import com.github.ryjen.jokeapp.ui.components.ReduxViewModel

class FavoritesViewModel(
    private val getFavoriteJokes: GetFavoriteJokes,
    private val removeFavoriteJoke: RemoveFavoriteJoke
) : ReduxViewModel<FavoritesState, FavoritesAction>() {

    override val store =
        ReduxStore<FavoritesState, FavoritesAction>(FavoritesState()) + FavoritesReducer + this

    override suspend fun apply(
        state: FavoritesState,
        action: FavoritesAction,
        dispatch: ReduxDispatcher<FavoritesAction>
    ) {
        when (action) {
            is FavoritesAction.Init -> initialize()
            is FavoritesAction.Remove -> removeJoke(action.data)
            else -> Unit
        }
    }

    init {
        dispatch(FavoritesAction.Init)
    }

    private suspend fun initialize() {
        getFavoriteJokes().collect {
            when (it) {
                is Outcome.Success -> dispatch(FavoritesAction.Update(it.data))
                is Outcome.Failure -> dispatch(FavoritesAction.Error(it.error))
            }
        }
    }

    private suspend fun removeJoke(joke: Joke) {
        when (val res = removeFavoriteJoke(joke)) {
            is Outcome.Failure -> dispatch(FavoritesAction.Error(res.error))
            is Outcome.Success -> Unit
        }
    }
}
