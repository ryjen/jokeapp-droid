package com.github.ryjen.jokeapp.domain.arch

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import timber.log.Timber

/** Executes the use case asynchronously and returns an [Outcome].
 *
 * @return an [Outcome].
 *
 * @param coroutineDispatcher the dispatcher to dispatch the use case to.
 * @param block the block to execute.
 */
suspend fun <Out> dispatchUseCase(
    coroutineDispatcher: CoroutineDispatcher = Dispatchers.Default,
    block: suspend () -> Out
): Outcome<Out> {
    return try {
        withContext(coroutineDispatcher) {
            Outcome.Success(block())
        }
    } catch (e: Exception) {
        Timber.d(e)
        Outcome.Failure(e)
    }
}

/**
 * Executes business logic in its execute method and keep posting updates to the result as
 * [Outcome<R>].
 * Handling an exception (emit [Outcome.Failure] to the result) is the subclasses responsibility.
 */
suspend fun <Out> flowUseCase(
    coroutineDispatcher: CoroutineDispatcher = Dispatchers.Default,
    block: suspend () -> Flow<Outcome<Out>>
) = block().catch { e -> emit(Outcome.Failure(Exception(e))) }
    .flowOn(coroutineDispatcher)
