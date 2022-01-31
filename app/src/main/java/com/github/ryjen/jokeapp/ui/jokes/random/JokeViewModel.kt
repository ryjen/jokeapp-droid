package com.github.ryjen.jokeapp.ui.jokes.random

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.data.repository.JokeRepository
import com.github.ryjen.jokeapp.domain.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JokeViewModel @Inject constructor(
    private val repo: JokeRepository,
) : ViewModel() {

    // the current data
    private val data = MutableStateFlow<State<Joke>>(State.Loading)
    val state: StateFlow<State<Joke>> = data

    init {
        // fetch a random one
        viewModelScope.launch {
            repo.getRandomJoke()
                .catch {
                    data.value = State.Failure(it)
                }
                .collect {
                    data.value = State.Success(it)
                }
        }
    }

    val currentJoke: Joke?
       get() = (data.value as? State.Success)?.data

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
            repo.getRandomJoke()
                .collect {
                    data.value = State.Success(it)
                }
        }
    }
}
