package com.github.ryjen.jokeapp.data.repository.joke

import com.github.ryjen.jokeapp.data.api.JokeService
import com.github.ryjen.jokeapp.data.mapping.JokeMapper
import com.github.ryjen.jokeapp.data.model.Joke
import com.github.ryjen.jokeapp.domain.mapping.JokeMapper as DomainMapper

class RemoteDataSource(
    private val service: JokeService,
    private val jokeMapper: DomainMapper<Joke> = JokeMapper
) {
    suspend fun randomJoke() = service.getRandomJoke()?.let { jokeMapper(it) }

    suspend fun getJoke(id: String) = service.getJoke(id)?.let { jokeMapper(it) }
}
