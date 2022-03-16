package com.github.ryjen.jokeapp.ui.jokes.random

import com.github.ryjen.jokeapp.domain.arch.redux.ReduxState
import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.ui.arch.Failure

data class RandomJokeState(
    val joke: Joke? = null,
    val error: Failure? = null
) : ReduxState
