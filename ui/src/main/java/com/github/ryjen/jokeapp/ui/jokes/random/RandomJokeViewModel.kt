package com.github.ryjen.jokeapp.ui.jokes.random

import androidx.lifecycle.viewModelScope
import com.github.ryjen.jokeapp.domain.arch.Outcome
import com.github.ryjen.jokeapp.domain.arch.redux.ReduxReducer
import com.github.ryjen.jokeapp.domain.arch.redux.applyMiddleware
import com.github.ryjen.jokeapp.domain.arch.redux.createAsyncThunk
import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.domain.usecase.AddFavoriteJoke
import com.github.ryjen.jokeapp.domain.usecase.GetRandomJoke
import com.github.ryjen.jokeapp.domain.usecase.RemoveFavoriteJoke
import com.github.ryjen.jokeapp.ui.R
import com.github.ryjen.jokeapp.ui.components.ViewModelReduxStore
import com.github.ryjen.jokeapp.ui.navigation.Router
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.onEach

class RandomJokeViewModel(
    private val getRandomJoke: GetRandomJoke,
    private val addFavoriteJoke: AddFavoriteJoke,
    private val removeFavoriteJoke: RemoveFavoriteJoke,
    private val router: Router
) : ViewModelReduxStore<RandomJokeState, RandomJokeAction>() {

    override val initialState = RandomJokeState()

    override fun reducer(): ReduxReducer<RandomJokeState, RandomJokeAction> = { state, action ->
        when (action) {
            is RandomJokeAction.Init -> state.copy(
                joke = null
            )
            is RandomJokeAction.Refresh -> state.copy(
                joke = action.data
            )
            is RandomJokeAction.Error -> state.copy(
                error = action.data
            )
            is RandomJokeAction.UnFavorite -> state.copy(
                joke = action.data.copy(isFavorite = false)
            )
            is RandomJokeAction.Favorite -> {
                state.copy(
                    joke = action.data.copy(isFavorite = true)
                )
            }
            else -> state
        }
    }

    private val thunk = createAsyncThunk<RandomJokeState, RandomJokeAction> { state, action, _ ->
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

    override fun middleware() = applyMiddleware(viewModelScope, thunk)

    init {
        dispatch(RandomJokeAction.Init)
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
    private suspend fun refreshJoke() {
        // fetch a random jokes
        getRandomJoke(Unit)
            .firstOrNull()?.let { res ->
                when (res) {
                    is Outcome.Success -> dispatch(RandomJokeAction.Refresh(res.data))
                    is Outcome.Failure -> dispatch(RandomJokeAction.Error(res.error))
                }
            }
    }

    private suspend fun startRandomizingJokes() {
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
