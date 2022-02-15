package com.github.ryjen.jokeapp.ui.arch

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed class Failure {
    data class Error(val value: Throwable): Failure()
    data class Message(val value: String): Failure()
    data class Resource(@StringRes val value: Int): Failure()
    
    @Composable
    fun message(): String {
        return when(this) {
            is Error -> value.message ?: value.toString()
            is Message -> value
            is Resource -> stringResource(id = value)
        }
    }
}
