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
    private val repository: JokeRepository,
    private val randomJokeDelayTime: Long = 60000L
) : FlowUseCase<Long?, Joke>(dispatcher.default()) {

    override fun execute(params: Long?): Flow<Outcome<Joke>> = flow {
        while (true) {
            if (networkAvailable()) {
                repository.remote.getRandomJoke()?.let {
                    repository.local.cacheJoke(it)
                    emit(Outcome.Success(it))
                }
            } else {
                repository.async.getRandomJoke()?.let {
                    emit(Outcome.Success(it))
                }
            }
            delay(params ?: randomJokeDelayTime)
        }
    }

    operator fun invoke() = super.invoke(null)
}

