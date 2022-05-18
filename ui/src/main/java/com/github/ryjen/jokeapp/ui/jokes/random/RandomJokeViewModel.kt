package com.github.ryjen.jokeapp.ui.jokes.random

import com.github.ryjen.jokeapp.domain.arch.redux.ReduxDispatcher
import com.github.ryjen.jokeapp.domain.arch.redux.ReduxStore
import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.domain.usecase.AddFavoriteJoke
import com.github.ryjen.jokeapp.domain.usecase.GetRandomJoke
import com.github.ryjen.jokeapp.domain.usecase.RemoveFavoriteJoke
import com.github.ryjen.jokeapp.ui.R
import com.github.ryjen.jokeapp.ui.components.ReduxViewModel
import com.github.ryjen.jokeapp.ui.navigation.Router
import kotlinx.coroutines.flow.firstOrNull

class RandomJokeViewModel(
    private val router: Router,
    private val getRandomJoke: GetRandomJoke,
    private val addFavoriteJoke: AddFavoriteJoke,
    private val removeFavoriteJoke: RemoveFavoriteJoke,
) : ReduxViewModel<RandomJokeState, RandomJokeAction>() {

    override val store =
        ReduxStore<RandomJokeState, RandomJokeAction>(RandomJokeState()) + RandomJokeReducer + this

    init {
        store.dispatch(RandomJokeAction.Init)
    }

    override suspend fun applyMiddleware(
        state: RandomJokeState,
        action: RandomJokeAction,
        dispatch: ReduxDispatcher<RandomJokeAction>
    ) {
        when (action) {
            is RandomJokeAction.Init -> startRandomizingJokes()
            is RandomJokeAction.RefreshClick -> refreshJoke()
            is RandomJokeAction.Favorite -> state.joke?.let {
                addJokeToFavorites(it)
            }
            is RandomJokeAction.UnFavorite ->
                state.joke?.let {
                    removeJokeFromFavorites(it)
                }
            else -> Unit
        }
    }

    // add the current data to favourites
    private suspend fun addJokeToFavorites(joke: Joke) {
        addFavoriteJoke(joke).onSuccess {
            router.showSuccess(R.string.favorite_added)
        }.onFailure {
            router.showDanger(R.string.unable_to_add_favorite)
        }
    }

    private suspend fun removeJokeFromFavorites(joke: Joke) {
        removeFavoriteJoke(joke).onSuccess {
            router.showInfo(R.string.favorite_removed)
        }.onFailure {
            router.showDanger(R.string.unable_to_remove_favorite)
        }
    }

    private suspend fun refreshJoke() {
        getRandomJoke()
            .firstOrNull()?.let { outcome ->
                outcome.onSuccess {
                    dispatch(RandomJokeAction.Refresh(it))
                }.onFailure {
                    dispatch(RandomJokeAction.Error(it))
                }
            }
    }

    private suspend fun startRandomizingJokes() {
        getRandomJoke()
            .collect { outcome ->
                outcome.onSuccess {
                    dispatch(RandomJokeAction.Refresh(it))
                }.onFailure {
                    dispatch(RandomJokeAction.Error(it))
                }
            }
    }
}
