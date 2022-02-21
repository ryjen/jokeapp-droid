package com.github.ryjen.jokeapp.domain.usecase

import com.github.ryjen.jokeapp.domain.arch.CoroutineUseCase
import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.domain.repository.joke.JokeRepository
import com.github.ryjen.jokeapp.meta.coroutine.DispatcherProvider

class AddFavoriteJoke(
    dispatcher: DispatcherProvider,
    private val repository: JokeRepository
) : CoroutineUseCase<Joke, Unit>(dispatcher.default()) {

    override suspend fun execute(params: Joke) {
        repository.async.addFavorite(params)
    }
}
