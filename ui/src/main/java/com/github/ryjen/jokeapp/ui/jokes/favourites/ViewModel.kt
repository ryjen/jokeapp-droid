package com.github.ryjen.jokeapp.ui.jokes.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ryjen.jokeapp.domain.arch.redux.ReduxDispatcher
import com.github.ryjen.jokeapp.domain.arch.redux.ReduxEffect
import com.github.ryjen.jokeapp.domain.facades.JokeFacade
import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.ui.arch.ReduxViewModel

class FavoritesViewModel(
    private val facade: JokeFacade
) : ViewModel(), ReduxViewModel<FavoritesState, FavoritesViewState, FavoritesAction>,
    ReduxEffect<FavoritesState, FavoritesAction> {

    override val store = FavoritesStore(viewModelScope)

    init {
        store.addEffect(this).dispatch(FavoritesAction.Init)
    }

    override fun mapViewState(state: FavoritesState) = FavoritesViewState(
        jokes = state.jokes
    )

    override suspend fun applyEffect(
        state: FavoritesState,
        action: FavoritesAction,
        dispatcher: ReduxDispatcher<FavoritesAction>
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
