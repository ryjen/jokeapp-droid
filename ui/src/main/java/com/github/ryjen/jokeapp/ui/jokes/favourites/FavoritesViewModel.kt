package com.github.ryjen.jokeapp.ui.jokes.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ryjen.jokeapp.domain.arch.Outcome
import com.github.ryjen.jokeapp.domain.arch.redux.ReduxActionHandler
import com.github.ryjen.jokeapp.domain.arch.redux.ReduxReducer
import com.github.ryjen.jokeapp.domain.arch.redux.ReduxStore
import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.domain.usecase.GetFavoriteJokes
import com.github.ryjen.jokeapp.domain.usecase.RemoveFavoriteJoke
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val getFavoriteJokes: GetFavoriteJokes,
    private val removeFavoriteJoke: RemoveFavoriteJoke
) : ViewModel(), ReduxActionHandler<FavoritesActions> {

    private val reducer: ReduxReducer<FavoritesState, FavoritesActions> = { state, action ->
        when (action) {
            is FavoritesActions.Update ->
                state.copy(jokes = action.data)
            is FavoritesActions.Remove -> {
                removeJoke(action.data)
                state
            }
            is FavoritesActions.Error -> {
                state.copy(error = action.data)
            }
        }
    }

    private val store = ReduxStore(FavoritesState(), reducer)

    val state = store.stateAsStateFlow()

    init {
        viewModelScope.launch {
            getFavoriteJokes().collect {
                when (it) {
                    is Outcome.Success -> store.dispatch(FavoritesActions.Update(it.data))
                    is Outcome.Failure -> store.dispatch(FavoritesActions.Error(it.error))
                }
            }
        }
    }

    override fun onAction(action: FavoritesActions) {
        store.dispatch(action)
    }

    private fun removeJoke(joke: Joke) {
        viewModelScope.launch {
            when (val res = removeFavoriteJoke(joke)) {
                is Outcome.Failure -> store.dispatch(FavoritesActions.Error(res.error))
                is Outcome.Success -> store
            }
        }
    }
}
