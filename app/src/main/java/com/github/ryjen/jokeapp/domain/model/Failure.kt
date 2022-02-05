package com.github.ryjen.jokeapp.domain.model

import androidx.annotation.StringRes

sealed class Failure {
    data class Error(val data: Throwable): Failure()
    data class Message(val message: String): Failure()
    data class Resource(@StringRes val message: Int): Failure()
}
