package com.github.ryjen.jokeapp.domain.arch

sealed class Outcome<out O> {
    data class Success<out T>(val data: T) : Outcome<T>()

    data class Failure(val error: Throwable) : Outcome<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success -> "Success[data=$data]"
            is Failure -> "Error[exception=$error]"
        }
    }

    suspend fun <T> onSuccess(block: suspend (O) -> T) = when (this) {
        is Success -> Success(block(data))
        is Failure -> this
    }

    suspend fun onFailure(block: suspend (Throwable) -> Unit): Outcome<O> = when (this) {
        is Success -> this
        is Failure -> {
            block(error)
            this
        }
    }
}

