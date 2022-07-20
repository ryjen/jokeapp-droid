package com.github.ryjen.jokeapp.domain.usecase

import com.github.ryjen.jokeapp.domain.arch.Outcome
import com.github.ryjen.jokeapp.domain.arch.flowUseCase
import com.github.ryjen.jokeapp.domain.repository.joke.JokeRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

class GetFavoriteJokes(
    private val repo: JokeRepository,
) {
    suspend operator fun invoke() = flowUseCase {
        repo.getFavoriteJokes()
            .distinctUntilChanged()
            .catch { e ->
                Outcome.Failure(e)
            }.map { jokes ->
                Outcome.Success(jokes)
            }
    }
}
