package com.github.ryjen.jokeapp.ui.jokes.random

import com.github.ryjen.jokeapp.domain.arch.Outcome
import com.github.ryjen.jokeapp.domain.arch.redux.ReduxDispatcher
import com.github.ryjen.jokeapp.domain.arch.redux.ReduxStore
import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.domain.usecase.AddFavoriteJoke
import com.github.ryjen.jokeapp.domain.usecase.GetRandomJoke
import com.github.ryjen.jokeapp.domain.usecase.RemoveFavoriteJoke
import com.github.ryjen.jokeapp.ui.R
import com.github.ryjen.jokeapp.ui.components.ReduxViewModel
import com.github.ryjen.jokeapp.ui.navigation.Router
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.onEach

class RandomJokeViewModel(
    private val router: Router,
    private val getRandomJoke: GetRandomJoke,
    private val addFavoriteJoke: AddFavoriteJoke,
    private val removeFavoriteJoke: RemoveFavoriteJoke,
) : ReduxViewModel<RandomJokeState, RandomJokeAction>() {

    override val store: ReduxStore<RandomJokeState, RandomJokeAction> =
        ReduxStore<RandomJokeState, RandomJokeAction>(RandomJokeState()) + RandomJokeReducer + this

    init {
        store.dispatch(RandomJokeAction.Init)
    }

    override suspend fun apply(
        state: RandomJokeState,
        action: RandomJokeAction,
        dispatch: ReduxDispatcher<RandomJokeAction>
    ) {
        when (action) {
            is RandomJokeAction.Init -> startRandomizingJokes(dispatch)
            is RandomJokeAction.RefreshClick -> refreshJoke(dispatch)
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
        when (addFavoriteJoke(joke)) {
            is Outcome.Success ->
                router.showSuccess(R.string.favorite_added)
            is Outcome.Failure ->
                router.showDanger(R.string.unable_to_add_favorite)
        }
    }

    // remove the current data from favourites
    private suspend fun removeJokeFromFavorites(joke: Joke) {
        when (removeFavoriteJoke(joke)) {
            is Outcome.Success -> router.showInfo(R.string.favorite_removed)
            is Outcome.Failure -> router.showDanger(R.string.unable_to_remove_favorite)
        }
    }

    // fetch new random data
    private suspend fun refreshJoke(dispatch: ReduxDispatcher<RandomJokeAction>) {
        // fetch a random jokes
        getRandomJoke(Unit)
            .firstOrNull()?.let { res ->
                when (res) {
                    is Outcome.Success -> dispatch(RandomJokeAction.Refresh(res.data))
                    is Outcome.Failure -> dispatch(RandomJokeAction.Error(res.error))
                }
            }
    }

    private suspend fun startRandomizingJokes(dispatch: ReduxDispatcher<RandomJokeAction>) {
        // fetch a random jokes
        getRandomJoke(Unit)
            .onEach {
                when (it) {
                    is Outcome.Success -> dispatch(RandomJokeAction.Refresh(it.data))
                    is Outcome.Failure -> dispatch(RandomJokeAction.Error(it.error))
                }
            }.collect()
    }
}
