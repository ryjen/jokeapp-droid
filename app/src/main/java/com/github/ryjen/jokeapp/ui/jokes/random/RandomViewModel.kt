package com.github.ryjen.jokeapp.ui.jokes.random

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ryjen.jokeapp.data.repository.JokeRepository
import com.github.ryjen.jokeapp.domain.arch.redux.ReduxReducer
import com.github.ryjen.jokeapp.domain.arch.redux.ReduxStore
import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.ui.arch.Failure
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class RandomViewModel(
    private val repo: JokeRepository,
) : ViewModel() {

    private val reducer: ReduxReducer<JokeState, JokeActions> = { state, action ->
        when (action) {
            is JokeActions.Refresh -> state.copy(
                joke = action.data
            )
            is JokeActions.Error -> state.copy(
                error = action.data
            )
            is JokeActions.Remove -> state.copy(
                joke = action.data.copy(isFavorite = false)
            )
            is JokeActions.Add -> state.copy(
                joke = action.data.copy(isFavorite = true)
            )
        }
    }

    private val store = ReduxStore(JokeState(), reducer)

    val state = store.stateAsStateFlow()

    init {
        startRandomizingJokes()
    }

    // add the current data to favourites
    fun addJokeToFavorites(joke: Joke) {
        store.dispatch(JokeActions.Add(joke))
        viewModelScope.launch {
            repo.addFavorite(joke)
        }
    }

    // remove the current data from favourites
    fun removeJokeFromFavorites(joke: Joke) {
        store.dispatch(JokeActions.Remove(joke))
        viewModelScope.launch {
            repo.removeFavorite(joke)
        }
    }

    // fetch new random data
    fun refreshJoke() {
        viewModelScope.launch {
            repo.getRandomJoke()?.let {
                store.dispatch(JokeActions.Refresh(it))
            }
        }
    }

    private fun startRandomizingJokes() {
        // fetch a random jokes
        viewModelScope.launch {
            repo.observeRandomJoke()
                .catch {
                    store.dispatch(JokeActions.Error(it))
                }
                .collect {
                    store.dispatch(JokeActions.Refresh(it))
                }
        }
    }
}
