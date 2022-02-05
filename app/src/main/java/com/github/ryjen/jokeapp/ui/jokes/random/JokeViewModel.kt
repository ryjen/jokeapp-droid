package com.github.ryjen.jokeapp.ui.jokes.random

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ryjen.jokeapp.data.repository.JokeRepository
import com.github.ryjen.jokeapp.domain.arch.redux.ErrorReducer
import com.github.ryjen.jokeapp.domain.arch.redux.ReduxReducer
import com.github.ryjen.jokeapp.domain.arch.redux.ReduxStore
import com.github.ryjen.jokeapp.domain.arch.redux.combineReducers
import com.github.ryjen.jokeapp.domain.model.Joke
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class JokeViewModel(
    private val repo: JokeRepository,
) : ViewModel() {

    private val reducer: ReduxReducer<JokeState, JokeActions> = { state, action ->
        when (action) {
            is JokeActions.Refresh -> state.copy(
                joke = action.data
            )
        }
    }

    private val errorHandler: ErrorReducer<JokeState> = { state, action ->
        state.copy(error = action.error)
    }

    private val store = ReduxStore(JokeState(), combineReducers(reducer, errorHandler))

    val state = store.stateAsStateFlow()

    init {
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

    val currentJoke: Joke?
        get() = store.currentState.joke

    // add the current data to favourites
    fun addJokeToFavorites(joke: Joke) {
        viewModelScope.launch {
            repo.addFavorite(joke)
        }
    }

    // remove the current data from favourites
    fun removeJokeFromFavorites(joke: Joke) {
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
}
