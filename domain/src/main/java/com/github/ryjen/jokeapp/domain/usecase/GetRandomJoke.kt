package com.github.ryjen.jokeapp.domain.usecase

import com.github.ryjen.jokeapp.domain.arch.Outcome
import com.github.ryjen.jokeapp.domain.arch.flowUseCase
import com.github.ryjen.jokeapp.domain.repository.joke.JokeRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class GetRandomJoke(
    private val networkAvailable: GetNetworkAvailability,
    private val repository: JokeRepository,
    private val randomJokeDelayTime: Long = 60000L
) {

    suspend operator fun invoke(duration: Long = randomJokeDelayTime) = flowUseCase {
        execute(duration)
    }

    private fun execute(duration: Long) = flow {
        while (true) {
            if (networkAvailable()) {
                repository.getRandomJoke()?.let {
                    repository.cacheJoke(it)
                    emit(Outcome.Success(it))
                }
            } else {
                repository.getRandomJoke()?.let {
                    emit(Outcome.Success(it))
                }
            }
            delay(duration)
        }
    }
}

