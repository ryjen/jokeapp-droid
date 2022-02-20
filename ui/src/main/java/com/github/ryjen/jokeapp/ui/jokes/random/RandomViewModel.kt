package com.github.ryjen.jokeapp.ui.jokes.random

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ryjen.jokeapp.domain.repository.joke.JokeRepository
import com.github.ryjen.jokeapp.domain.arch.Outcome
import com.github.ryjen.jokeapp.domain.arch.redux.ReduxReducer
import com.github.ryjen.jokeapp.domain.arch.redux.ReduxStore
import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.domain.usecase.GetRandomJoke
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class RandomJokeViewModel(
    private val repo: JokeRepository,
    private val getRandomJoke: GetRandomJoke
) : ViewModel() {

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

    // add the current data to favourites
    fun addJokeToFavorites(joke: Joke) {
        store.dispatch(JokeActions.Favorite(joke))
        viewModelScope.launch {
            repo.async.addFavorite(joke)
        }
    }

    // remove the current data from favourites
    fun removeJokeFromFavorites(joke: Joke) {
        store.dispatch(JokeActions.UnFavorite(joke))
        viewModelScope.launch {
            repo.async.removeFavorite(joke)
        }
    }

    // fetch new random data
    fun refreshJoke() {
        viewModelScope.launch {
            repo.async.getRandomJoke()?.let {
                store.dispatch(JokeActions.Refresh(it))
            }
        }
    }

    private fun startRandomizingJokes() {
        // fetch a random jokes
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
