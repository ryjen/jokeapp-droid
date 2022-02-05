package com.github.ryjen.jokeapp.domain.usecase

import com.github.ryjen.jokeapp.data.repository.JokeRepository
import com.github.ryjen.jokeapp.domain.arch.CoroutineUseCase
import com.github.ryjen.jokeapp.domain.arch.FlowUseCase
import com.github.ryjen.jokeapp.domain.arch.Outcome
import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.meta.coroutines.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class GetFavoriteJokes(
    dispatcher: DispatcherProvider,
    private val repo: JokeRepository,
): FlowUseCase<Unit, List<Joke>>(dispatcher.default()) {

    override fun execute(parameters: Unit): Flow<Outcome<List<Joke>>> {
        return repo.getFavoriteJokes()
            .distinctUntilChanged()
            .catch { e ->
                Outcome.Failure(e)
            }.map { joke ->
                Outcome.Success(joke)
            }
    }

    operator fun invoke() = this.invoke(Unit)
}
