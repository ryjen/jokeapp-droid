package com.github.ryjen.jokeapp.ui.jokes.random

import com.github.ryjen.jokeapp.domain.arch.redux.ReduxDispatcher
import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.ui.R
import com.github.ryjen.jokeapp.ui.arch.redux.ViewEffect
import com.github.ryjen.jokeapp.ui.navigation.Router
import kotlinx.coroutines.CoroutineScope


class RandomJokeEffects(
    scope: CoroutineScope,
    private val router: Router,
    private val facade: RandomJokeFacade,
    dispatcher: ReduxDispatcher<RandomJokeAction>
) : ViewEffect<RandomJokeState, RandomJokeAction>(scope),
    ReduxDispatcher<RandomJokeAction> by dispatcher {

    override suspend fun effects(
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


    private suspend fun add(joke: Joke) = facade.add(joke).onSuccess {
        router.showSuccess(R.string.favorite_added)
    }.onFailure {
        router.showDanger(R.string.unable_to_add_favorite)
    }

    suspend fun remove(joke: Joke) {
        facade.remove(joke).onSuccess {
            router.showInfo(R.string.favorite_removed)
        }.onFailure {
            router.showDanger(R.string.unable_to_remove_favorite)
        }
    }

    private suspend fun refresh() {
        facade.refresh()?.let { outcome ->
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
