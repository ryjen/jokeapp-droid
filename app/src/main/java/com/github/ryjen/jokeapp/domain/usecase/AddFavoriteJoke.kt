package com.github.ryjen.jokeapp.domain.usecase

import com.github.ryjen.jokeapp.data.repository.JokeRepository
import com.github.ryjen.jokeapp.domain.arch.CoroutineUseCase
import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.meta.coroutines.DispatcherProvider

class AddFavoriteJoke(
    dispatcher: DispatcherProvider,
    private val repository: JokeRepository
) : CoroutineUseCase<Joke, Unit>(dispatcher.default()) {

    override suspend fun execute(parameters: Joke) {
        repository.addFavorite(parameters)
    }
}
