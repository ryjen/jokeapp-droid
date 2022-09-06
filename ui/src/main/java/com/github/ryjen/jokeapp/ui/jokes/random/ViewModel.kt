package com.github.ryjen.jokeapp.ui.jokes.random

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ryjen.jokeapp.domain.arch.redux.ReduxDispatcher
import com.github.ryjen.jokeapp.domain.arch.redux.ReduxEffect
import com.github.ryjen.jokeapp.domain.facades.JokeFacade
import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.ui.R
import com.github.ryjen.jokeapp.ui.arch.ReduxViewModel
import com.github.ryjen.jokeapp.ui.navigation.Router

class RandomJokeViewModel(
    private val router: Router,
    private val facade: JokeFacade,
) : ViewModel(),
    ReduxViewModel<RandomJokeState, RandomJokeViewState, RandomJokeAction>,
    ReduxEffect<RandomJokeState, RandomJokeAction> {

    override val store = RandomJokeStore(viewModelScope)

    init {
        // viewModel adds the effects to the store
        store.addEffect(this).dispatch(RandomJokeAction.Init)
    }

    override fun mapViewState(state: RandomJokeState) = RandomJokeViewState(
        joke = state.joke,
        error = state.error
    )

    override suspend fun applyEffect(
        state: RandomJokeState,
        action: RandomJokeAction,
        dispatcher: ReduxDispatcher<RandomJokeAction>
    ) {
        when (action) {
            is RandomJokeAction.Init -> randomize()
            is RandomJokeAction.RefreshClick -> refresh()
            is RandomJokeAction.Favorite -> state.joke?.let {
                add(it)
            }
            is RandomJokeAction.UnFavorite -> state.joke?.let { remove(it) }
            else -> Unit
        }
    }

    private suspend fun add(joke: Joke) = facade.addFavorite(joke).onSuccess {
        router.showSuccess(R.string.favorite_added)
    }.onFailure {
        router.showDanger(R.string.unable_to_add_favorite)
    }

    private suspend fun remove(joke: Joke) {
        facade.removeFavorite(joke).onSuccess {
            router.showInfo(R.string.favorite_removed)
        }.onFailure {
            router.showDanger(R.string.unable_to_remove_favorite)
        }
    }

    private suspend fun refresh() {
        facade.random()?.let { outcome ->
            outcome.onSuccess {
                dispatch(RandomJokeAction.Refresh(it))
            }.onFailure {
                dispatch(RandomJokeAction.Error(it))
            }
        }
    }

    private suspend fun randomize() {
        facade.randomize { outcome ->
            outcome.onSuccess {
                dispatch(RandomJokeAction.Refresh(it))
            }.onFailure {
                dispatch(RandomJokeAction.Error(it))
            }
        }
    }
}
