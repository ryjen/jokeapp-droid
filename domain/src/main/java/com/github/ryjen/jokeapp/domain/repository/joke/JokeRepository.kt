package com.github.ryjen.jokeapp.domain.repository.joke

import com.github.ryjen.jokeapp.domain.model.Joke
import kotlinx.coroutines.flow.Flow

interface JokeRepository {
    // Observable data

    fun getFavoriteJokes(): Flow<List<Joke>>

    // Local data

    suspend fun getJoke(id: String): Joke?

    suspend fun getRandomJoke(): Joke?

    // Remote data

    suspend fun addFavorite(joke: Joke)

    suspend fun removeFavorite(joke: Joke)

    suspend fun cacheJoke(joke: Joke)
}
