package com.github.ryjen.jokeapp.ui.jokes.random

import com.github.ryjen.jokeapp.domain.arch.redux.ReduxAction
import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.ui.arch.Failure
import com.github.ryjen.jokeapp.ui.arch.redux.ReduxActionWithErrorCompanion
import com.github.ryjen.jokeapp.ui.arch.redux.ReduxStateWithError

data class JokeState(
    val joke: Joke? = null,
    override val error: Failure? = null
) : ReduxStateWithError

sealed class JokeActions : ReduxAction {
    companion object : ReduxActionWithErrorCompanion
    data class Refresh(val data: Joke) : JokeActions()
}
