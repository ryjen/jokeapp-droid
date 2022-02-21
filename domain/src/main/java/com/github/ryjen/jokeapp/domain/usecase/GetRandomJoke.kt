package com.github.ryjen.jokeapp.domain.usecase

import com.github.ryjen.jokeapp.domain.arch.FlowUseCase
import com.github.ryjen.jokeapp.domain.arch.Outcome
import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.domain.repository.joke.JokeRepository
import com.github.ryjen.jokeapp.meta.coroutine.DispatcherProvider
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRandomJoke(
    dispatcher: DispatcherProvider,
    private val networkAvailable: GetNetworkAvailability,
    private val repository: JokeRepository
) : FlowUseCase<Unit, Joke>(dispatcher.default()) {

    private val randomJokeDelayTime: Long = 60000L

    override fun execute(params: Unit): Flow<Outcome<Joke>> = flow {
        while (true) {
            if (networkAvailable()) {
                repository.remote.getRandomJoke()?.let {
                    repository.local.saveJoke(it)
                    emit(Outcome.Success(it))
                }
            } else {
                repository.local.getRandomJoke()?.let {
                    emit(Outcome.Success(it))
                }
            }
            delay(randomJokeDelayTime)
        }
    }
}

