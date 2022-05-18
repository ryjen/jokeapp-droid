package com.github.ryjen.jokeapp.data.repository.joke

import com.github.ryjen.jokeapp.data.api.JokeService
import com.github.ryjen.jokeapp.data.mapping.JokeMapper

class RemoteDataSource(
    private val service: JokeService,
    private val mapper: JokeMapper = JokeMapper
) {
    suspend fun randomJoke() = service.getRandomJoke()?.let { mapper(it) }

    suspend fun getJoke(id: String) = service.getJoke(id)?.let { mapper(it) }
}
