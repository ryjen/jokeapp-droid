package com.github.ryjen.jokeapp.ui.jokes.random

import com.github.ryjen.jokeapp.domain.arch.redux.ErrorActionCompanion
import com.github.ryjen.jokeapp.domain.arch.redux.ErrorState
import com.github.ryjen.jokeapp.domain.arch.redux.ReduxAction
import com.github.ryjen.jokeapp.domain.model.Failure
import com.github.ryjen.jokeapp.domain.model.Joke

data class JokeState(
    val joke: Joke? = null,
    override val error: Failure? = null
): ErrorState

sealed class JokeActions: ReduxAction {
    companion object: ErrorActionCompanion
    data class Refresh(val data: Joke): JokeActions()
}
