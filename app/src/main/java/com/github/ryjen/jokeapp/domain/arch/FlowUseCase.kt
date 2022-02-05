package com.github.ryjen.jokeapp.domain.arch

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

/**
 * Executes business logic in its execute method and keep posting updates to the result as
 * [Outcome<R>].
 * Handling an exception (emit [Outcome.Failure] to the result) is the subclasses's responsibility.
 */
abstract class FlowUseCase<in P, R>(
    private val coroutineDispatcher: CoroutineDispatcher
) {
    operator fun invoke(parameters: P): Flow<Outcome<R>> = execute(parameters)
        .catch { e -> emit(Outcome.Failure(Exception(e))) }
        .flowOn(coroutineDispatcher)

    protected abstract fun execute(parameters: P): Flow<Outcome<R>>
}
