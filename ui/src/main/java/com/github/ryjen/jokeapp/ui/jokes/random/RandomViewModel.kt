package com.github.ryjen.jokeapp.ui.jokes.random

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ryjen.jokeapp.domain.arch.Outcome
import com.github.ryjen.jokeapp.domain.arch.redux.ReduxActionHandler
import com.github.ryjen.jokeapp.domain.arch.redux.ReduxReducer
import com.github.ryjen.jokeapp.domain.arch.redux.ReduxStore
import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.domain.usecase.AddFavoriteJoke
import com.github.ryjen.jokeapp.domain.usecase.GetRandomJoke
import com.github.ryjen.jokeapp.domain.usecase.RemoveFavoriteJoke
import com.github.ryjen.jokeapp.ui.R
import com.github.ryjen.jokeapp.ui.navigation.Router
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class RandomJokeViewModel(
    private val getRandomJoke: GetRandomJoke,
    private val addFavoriteJoke: AddFavoriteJoke,
    private val removeFavoriteJoke: RemoveFavoriteJoke,
    private val router: Router
) : ViewModel(), ReduxActionHandler<JokeActions> {

    private val reducer: ReduxReducer<JokeState, JokeActions> = { state, action ->
        when (action) {
            is JokeActions.Refresh -> state.copy(
                joke = action.data
            )
            is JokeActions.Error -> state.copy(
                error = action.data
            )
            is JokeActions.UnFavorite -> state.copy(
                joke = action.data.copy(isFavorite = false)
            )
            is JokeActions.Favorite -> {
                state.copy(
                    joke = action.data.copy(isFavorite = true)
                )
            }
        }
    }

    private val store = ReduxStore(JokeState(), reducer)

    val state = store.stateAsStateFlow()

    init {
        startRandomizingJokes()
    }

    override fun onAction(action: JokeActions) {
        store.dispatch(action)
    }

    // add the current data to favourites
    fun addJokeToFavorites(joke: Joke) {
        store.dispatch(JokeActions.Favorite(joke))
        viewModelScope.launch {
            when (addFavoriteJoke(joke)) {
                is Outcome.Success ->
                    router.showSuccess(R.string.favorite_added)
                is Outcome.Failure ->
                    router.showDanger(R.string.unable_to_add_favorite)
            }
        }
    }

    // remove the current data from favourites
    fun removeJokeFromFavorites(joke: Joke) {
        store.dispatch(JokeActions.UnFavorite(joke))
        viewModelScope.launch {
            when (removeFavoriteJoke(joke)) {
                is Outcome.Success -> router.showSuccess(R.string.favorite_removed)
                is Outcome.Failure -> router.showDanger(R.string.unable_to_remove_favorite)
            }
        }
    }

    // fetch new random data
    fun refreshJoke() {
        viewModelScope.launch {
            // fetch a random jokes
            getRandomJoke(Unit)
                .firstOrNull()?.let { res ->
                    when (res) {
                        is Outcome.Success -> store.dispatch(JokeActions.Refresh(res.data))
                        is Outcome.Failure -> store.dispatch(JokeActions.Error(res.error))
                    }
                }
        }
    }

    private fun startRandomizingJokes() {
        // fetch a random jokes
        store.dispatch(JokeActions.Refresh(null))
        getRandomJoke(Unit)
            .onEach {
                when (it) {
                    is Outcome.Success -> store.dispatch(JokeActions.Refresh(it.data))
                    is Outcome.Failure -> store.dispatch(JokeActions.Error(it.error))
                }
            }
            .launchIn(viewModelScope)
    }
}
