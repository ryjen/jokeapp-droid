package com.atimi.jokeapp.ui.jokes

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atimi.jokeapp.DispatcherProvider
import com.atimi.jokeapp.R
import com.atimi.jokeapp.model.Joke
import com.atimi.jokeapp.storage.JokeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class JokesViewModel @Inject constructor(
    private val repo: JokeRepository
) : ViewModel() {

    val data = MutableLiveData<MutableList<Joke>>()

    init {
        viewModelScope.launch {
            data.postValue(repo.getFavouriteJokes().toMutableList())
        }
    }

    fun removeJoke(position: Int): Joke? {

        val joke = data.value?.removeAt(position) ?: return null

        viewModelScope.launch {
            repo.removeJokes(joke)
        }

       data.postValue(data.value)

        return joke
    }

    fun undoRemovedJoke(position: Int, joke: Joke) {

        viewModelScope.launch {
            repo.addJokes(joke)
        }

        data.value?.add(position, joke)

        data.postValue(data.value)
    }

}