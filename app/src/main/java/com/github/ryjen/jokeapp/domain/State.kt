package com.github.ryjen.jokeapp.domain

sealed class State<out T> {
    object Loading: State<Nothing>()

    data class Success<out T>(val data: T) : State<T>()

    data class Failure(val error: Throwable) : State<Nothing>()
}

