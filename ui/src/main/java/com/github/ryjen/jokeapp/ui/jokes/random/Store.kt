package com.github.ryjen.jokeapp.ui.jokes.random

import com.github.ryjen.jokeapp.domain.arch.redux.ScopedReduxFlowStore
import kotlinx.coroutines.CoroutineScope

class RandomJokeStore(
    override val reduxScope: CoroutineScope
) : ScopedReduxFlowStore<RandomJokeState, RandomJokeAction>(
    reduxScope, RandomJokeState()
) {
    init {
        addReducer(::randomJokeReducer)
    }
}
