package com.github.ryjen.jokeapp.ui.jokes.random

import com.github.ryjen.jokeapp.domain.arch.redux.FlowReduxStore
import kotlinx.coroutines.CoroutineScope

class RandomJokeStore(
    override val reduxScope: CoroutineScope
) :
    FlowReduxStore<RandomJokeState, RandomJokeAction>(
        RandomJokeState(), reduxScope
    ) {

    init {
        addReducer(::randomJokeReducer)
    }
}
