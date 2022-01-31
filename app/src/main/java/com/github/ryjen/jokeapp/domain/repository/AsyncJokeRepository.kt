package com.github.ryjen.jokeapp.domain.repository

import com.github.ryjen.jokeapp.domain.model.Joke

interface AsyncJokeRepository {

    suspend fun getJoke(jokeId: String): Joke?

    suspend fun addFavorite(joke: Joke)

    suspend fun removeFavorite(joke: Joke)
}
