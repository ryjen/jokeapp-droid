package com.github.ryjen.jokeapp.domain.repository.joke.source

import com.github.ryjen.jokeapp.domain.model.Joke

interface RemoteJokeRepository {

    suspend fun getJoke(jokeId: String): Joke?

    suspend fun getRandomJoke(): Joke?

}
