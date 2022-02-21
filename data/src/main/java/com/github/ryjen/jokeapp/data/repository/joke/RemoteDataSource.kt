package com.github.ryjen.jokeapp.data.repository.joke

import com.github.ryjen.jokeapp.data.api.JokeService

class RemoteDataSource(
    private val service: JokeService
) {
    suspend fun randomJoke() = service.getRandomJoke()?.asJoke()

    suspend fun getJoke(id: String) = service.getJoke(id)?.asJoke()
}
