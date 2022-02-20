package com.github.ryjen.jokeapp.domain.repository.joke.io

import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.domain.repository.joke.source.RemoteJokeRepository

interface AsyncJokeRepository : RemoteJokeRepository {

    suspend fun addFavorite(joke: Joke)

    suspend fun removeFavorite(joke: Joke)

    suspend fun saveJoke(joke: Joke)
}
