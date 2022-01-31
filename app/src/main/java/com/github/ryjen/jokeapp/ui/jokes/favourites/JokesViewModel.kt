package com.github.ryjen.jokeapp.ui.jokes.favourites

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
class JokesViewModel @Inject constructor(
    private val repo: JokeRepository
) : ViewModel() {

    private val jokes = mutableListOf<Joke>()
    private val state = MutableStateFlow<State<List<Joke>>>(State.Loading)
    val uiState: StateFlow<State<List<Joke>>> = state

    init {
        viewModelScope.launch {
            repo.getFavouriteJokes()
                .distinctUntilChanged()
                .catch { e ->
                    state.value = State.Failure(e)
                }.onEach { joke ->
                    jokes.add(joke)
                }.onCompletion {
                    state.value = State.Success(jokes)
                }
        }
    }

    fun removeFavorite(joke: Joke) {
        viewModelScope.launch {
            repo.removeFavorite(joke)
        }
    }

    fun undoRemoveFavorite(joke: Joke) {

        viewModelScope.launch {
            repo.addFavorite(joke)
        }
    }

}
