package com.github.ryjen.jokeapp.data.repository

import com.github.ryjen.jokeapp.data.storage.JokeDao
import com.github.ryjen.jokeapp.domain.model.Joke
import kotlinx.coroutines.flow.flow

class LocalDataSource(
    private val jokeDao: JokeDao
) {

    suspend fun randomJoke() = flow {
        jokeDao.getRandomJoke()?.let {
            emit(it)
        }
    }

    fun getFavoriteJokes() = jokeDao.getFavoriteJokes()

    suspend fun getJoke(id: String) = flow {
        jokeDao.getJoke(id)?.let {
            emit(it)
        }
    }

    suspend fun cacheJoke(vararg jokes: Joke) {
        jokeDao.insertJokes(*jokes)
    }

    suspend fun addFavorite(joke: Joke) {
        jokeDao.insertJokes(joke.copy(isFavorite = true))
    }

    suspend fun removeFavorite(joke: Joke) {
        jokeDao.deleteJokes(joke)
    }
}
