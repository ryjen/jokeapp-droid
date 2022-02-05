package com.github.ryjen.jokeapp.data.repository

import com.github.ryjen.jokeapp.data.api.JokeService
import com.github.ryjen.jokeapp.domain.model.Joke
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteDataSource(
    private val service: JokeService
) {
    fun randomJoke(): Flow<Joke> = flow {
        service.getRandomJoke()?.let {
            emit(it)
        }
    }

    suspend fun getJoke(id: String) = service.getJoke(id)
}
