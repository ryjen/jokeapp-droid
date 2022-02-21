package com.github.ryjen.jokeapp.domain.repository.joke.source

import com.github.ryjen.jokeapp.domain.model.Joke

interface LocalJokeRepository {
    suspend fun addFavorite(joke: Joke)

    suspend fun removeFavorite(joke: Joke)

    suspend fun cacheJoke(joke: Joke)
}
