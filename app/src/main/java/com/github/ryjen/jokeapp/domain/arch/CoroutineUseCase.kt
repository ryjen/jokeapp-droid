package com.github.ryjen.jokeapp.domain.arch

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber

/**
 * Executes business logic synchronously or asynchronously using Coroutines.
 */
abstract class CoroutineUseCase<in P, R>(
    private val coroutineDispatcher: CoroutineDispatcher
) {

    /** Executes the use case asynchronously and returns an [Outcome].
     *
     * @return an [Outcome].
     *
     * @param parameters the input parameters to run the use case with
     */
    suspend operator fun invoke(parameters: P): Outcome<R> {
        return try {
            // Moving all use case's executions to the injected dispatcher
            // In production code, this is usually the Default dispatcher (background thread)
            // In tests, this becomes a TestCoroutineDispatcher
            withContext(coroutineDispatcher) {
                execute(parameters).let {
                    Outcome.Success(it)
                }
            }
        } catch (e: Exception) {
            Timber.d(e)
            Outcome.Failure(e)
        }
    }

    /**
     * Override this to set the code to be executed.
     */
    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(parameters: P): R
}
