package com.github.ryjen.jokeapp.domain.usecase

import com.github.ryjen.jokeapp.domain.arch.dispatchUseCase
import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.domain.repository.joke.JokeRepository

class AddFavoriteJoke(
    private val repository: JokeRepository
) {
    suspend operator fun invoke(joke: Joke) = dispatchUseCase {
        repository.addFavorite(joke)
    }
}
