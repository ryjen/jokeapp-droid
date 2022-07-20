package com.github.ryjen.jokeapp.data.repository.joke

import com.github.ryjen.jokeapp.data.storage.Joke
import com.github.ryjen.jokeapp.data.storage.JokeDatabase
import com.squareup.sqldelight.runtime.coroutines.asFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalDataSource(
    private val db: JokeDatabase
) {
    fun getJoke(id: String) = db.jokeQueries.get(id).executeAsOneOrNull()

    fun getFavoriteJokes(): Flow<List<Joke>> =
        db.jokeQueries.favorites().asFlow().map { it.executeAsList() }

    fun deleteJoke(joke: Joke) = db.jokeQueries.delete(joke.key)

    fun updateFavorite(key: String, value: Boolean = true) =
        db.jokeQueries.updateFavorite(value, key)

    fun insertJoke(joke: Joke) =
        db.jokeQueries.insert(
            joke.key,
            joke.content,
            joke.created,
            joke.isFavorite
        )

    fun getRandomJoke() = db.jokeQueries.random().executeAsOneOrNull()
}
