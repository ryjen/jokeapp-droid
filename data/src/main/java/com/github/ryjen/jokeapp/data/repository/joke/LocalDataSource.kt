package com.github.ryjen.jokeapp.data.repository.joke

import com.github.ryjen.jokeapp.data.model.Joke
import com.github.ryjen.jokeapp.data.storage.AsyncJokeDao
import com.github.ryjen.jokeapp.data.storage.ObservableJokeDao
import com.github.ryjen.jokeapp.data.storage.SyncJokeDao

class LocalDataSource(
    val observable: ObservableJokeDao,
    val async: AsyncJokeDao,
    val sync: SyncJokeDao
) {

    suspend fun updateFavorite(joke: Joke) {
        async.insertJokes(joke.copy(isFavorite = true))
    }
}
