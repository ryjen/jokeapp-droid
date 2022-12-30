package com.github.ryjen.jokeapp.ui.jokes.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ryjen.jokeapp.domain.facades.JokeFacade
import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.ui.arch.ReduxViewModel
import com.micrantha.kredux.ReduxAction
import com.micrantha.kredux.ReduxDispatcher
import com.micrantha.kredux.ReduxEffect
import com.micrantha.kredux.coroutines.store.mappedFlowStoreOf

class FavoritesViewModel(
    private val facade: JokeFacade
) : ViewModel(), ReduxViewModel<FavoritesState, FavoritesViewState, FavoritesAction>,
    ReduxEffect<FavoritesState> {

    override val store =
        mappedFlowStoreOf(scoped = { viewModelScope },
            initialState = FavoritesState(),
            transform = {
                FavoritesViewState(it)
            })

    init {
        // viewModel adds the effects
        store.addEffect(this).dispatch(FavoritesAction.Init)
    }

    override suspend fun applyEffect(
        state: FavoritesState,
        action: ReduxAction,
        dispatcher: ReduxDispatcher
    ) {
        when (action) {
            is FavoritesAction.Init -> initialize()
            is FavoritesAction.Remove -> removeJoke(action.data)
            else -> Unit
        }
    }

    private suspend fun initialize() {
        facade.favorites { outcome ->
            outcome.onSuccess {
                dispatch(FavoritesAction.Update(it))
            }.onFailure {
                dispatch(FavoritesAction.Error(it))
            }
        }
    }

    private suspend fun removeJoke(joke: Joke) {
        facade.removeFavorite(joke)
            .onFailure {
                dispatch(FavoritesAction.Error(it))
            }
    }
}
