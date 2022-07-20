package com.github.ryjen.jokeapp.ui.jokes.favourites

import com.github.ryjen.jokeapp.domain.arch.redux.ReduxDispatcher
import com.github.ryjen.jokeapp.domain.facades.JokeFacade
import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.ui.arch.redux.ViewEffect
import kotlinx.coroutines.CoroutineScope

class FavoriteJokesEffects(
    scope: CoroutineScope,
    private val facade: JokeFacade,
    dispatcher: ReduxDispatcher<FavoritesAction>
) : ViewEffect<FavoritesState, FavoritesAction>(scope),
    ReduxDispatcher<FavoritesAction> by dispatcher {

    override suspend fun effects(
        state: FavoritesState,
        action: FavoritesAction,
        dispatcher: ReduxDispatcher<FavoritesAction>
    ) {
        when (action) {
            is FavoritesAction.Init -> initialize()
            is FavoritesAction.Remove -> removeJoke(action.data)
            else -> Unit
        }
    }

    private suspend fun initialize() {
        facade.favorites { outcome ->
            outcome.onSuccess {
                dispatch(FavoritesAction.Update(it))
            }.onFailure {
                dispatch(FavoritesAction.Error(it))
            }
        }
    }

    private suspend fun removeJoke(joke: Joke) {
        facade.removeFavorite(joke)
            .onFailure {
                dispatch(FavoritesAction.Error(it))
            }
    }
}
