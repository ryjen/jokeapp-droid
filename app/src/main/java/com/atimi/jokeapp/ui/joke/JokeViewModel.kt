package com.atimi.jokeapp.ui.joke

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atimi.jokeapp.DispatcherProvider
import com.atimi.jokeapp.model.Joke
import com.atimi.jokeapp.storage.JokeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JokeViewModel @Inject constructor(
    private val repo: JokeRepository,
    private val dispatchers: DispatcherProvider
) : ViewModel() {

    // the current data
    var data = MutableLiveData<Joke>()

    init {
        // if there is no current data...
        if (data.value == null) {
            // fetch a random one
            viewModelScope.launch {
                data.postValue(repo.getRandomJoke())
            }
        }
    }

    // a favourite mean it exists in the local database
    // the created field is populated when inserted
    val isFavourite: Boolean
        get() = data.value?.created != null

    // add the current data to favourites
    fun addJokeToFavourites() = data.value?.let { j ->
        viewModelScope.launch {
            repo.addJokes(j)
            // refresh from the db with the created timestamp
            // and post a new value to trigger UI changes
            data.postValue(repo.getJoke(j.id))
        }
    }

    // remove the current data from favourites
    fun removeJokeFromFavourites() = data.value?.let {
        viewModelScope.launch {
            repo.removeJokes(it)
            data.postValue(Joke(it.id, it.joke, it.status))
        }
    }

    // fetch new random data
    fun refreshJoke() = viewModelScope.launch {
        data.postValue(repo.getRandomJoke())
    }
}