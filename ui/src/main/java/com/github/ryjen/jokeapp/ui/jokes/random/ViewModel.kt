package com.github.ryjen.jokeapp.ui.jokes.random

import androidx.lifecycle.viewModelScope
import com.github.ryjen.jokeapp.domain.arch.redux.ReduxStore
import com.github.ryjen.jokeapp.domain.facades.JokeFacade
import com.github.ryjen.jokeapp.ui.arch.redux.ReduxViewModel
import com.github.ryjen.jokeapp.ui.navigation.Router

class RandomJokeViewModel(
    router: Router,
    facade: JokeFacade
) :
    ReduxViewModel<RandomJokeState, RandomJokeViewState, RandomJokeAction>() {

    override val store =
        ReduxStore<RandomJokeState, RandomJokeAction>(RandomJokeState()) + RandomJokeReducer + RandomJokeEffects(
            viewModelScope, router, facade, this
        )

    init {
        store.dispatch(RandomJokeAction.Init)
    }

    override fun mapViewState(state: RandomJokeState) = RandomJokeViewState(
        joke = state.joke,
        error = state.error
    )
}
