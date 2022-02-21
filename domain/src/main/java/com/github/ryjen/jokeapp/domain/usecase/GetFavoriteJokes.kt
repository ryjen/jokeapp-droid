package com.github.ryjen.jokeapp.domain.usecase

import com.github.ryjen.jokeapp.domain.arch.FlowUseCase
import com.github.ryjen.jokeapp.domain.arch.Outcome
import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.domain.repository.joke.JokeRepository
import com.github.ryjen.jokeapp.meta.coroutine.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

class GetFavoriteJokes(
    dispatcher: DispatcherProvider,
    private val repo: JokeRepository,
) : FlowUseCase<Unit, List<Joke>>(dispatcher.default()) {

    override fun execute(params: Unit): Flow<Outcome<List<Joke>>> {
        return repo.observable.getFavoriteJokes()
            .distinctUntilChanged()
            .catch { e ->
                Outcome.Failure(e)
            }.map { jokes ->
                Outcome.Success(jokes)
            }
    }

    operator fun invoke() = this.invoke(Unit)
}
