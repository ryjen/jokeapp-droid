package com.github.ryjen.jokeapp.domain.arch

sealed class Outcome<out O> {
    data class Success<out T>(val data: T): Outcome<T>()

    data class Failure(val error: Throwable) : Outcome<Nothing>()

    object Loading : Outcome<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Failure -> "Error[exception=$error]"
            Loading -> "Loading"
        }
    }
}

