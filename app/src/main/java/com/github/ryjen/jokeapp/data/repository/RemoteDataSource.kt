package com.github.ryjen.jokeapp.data.repository

import com.github.ryjen.jokeapp.data.api.JokeService
import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.domain.usecase.GetNetworkAvailability
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val service: JokeService) {
    val randomJoke: Flow<Joke> = flow {
        service.getRandomJoke()?.let {
            emit(it)
        }
    }

    suspend fun getJoke(id: String) = service.getJoke(id)
}
