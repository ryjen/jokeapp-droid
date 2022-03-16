package com.github.ryjen.jokeapp.ui.jokes.favourites

import androidx.lifecycle.viewModelScope
import com.github.ryjen.jokeapp.domain.arch.Outcome
import com.github.ryjen.jokeapp.domain.arch.redux.ReduxReducer
import com.github.ryjen.jokeapp.domain.arch.redux.applyMiddleware
import com.github.ryjen.jokeapp.domain.arch.redux.createAsyncThunk
import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.domain.usecase.GetFavoriteJokes
import com.github.ryjen.jokeapp.domain.usecase.RemoveFavoriteJoke
import com.github.ryjen.jokeapp.ui.components.ViewModelReduxStore

class FavoritesViewModel(
    private val getFavoriteJokes: GetFavoriteJokes,
    private val removeFavoriteJoke: RemoveFavoriteJoke
) : ViewModelReduxStore<FavoritesState, FavoritesAction>() {

    override val initialState = FavoritesState()

    override fun reducer(): ReduxReducer<FavoritesState, FavoritesAction> = { state, action ->
        when (action) {
            is FavoritesAction.Update ->
                state.copy(jokes = action.data)
            is FavoritesAction.Remove -> state.copy(
                jokes = state.jokes.minus(action.data)
            )
            is FavoritesAction.Error -> {
                state.copy(error = action.data)
            }
            else -> state
        }
    }

    private val thunk = createAsyncThunk<FavoritesState, FavoritesAction> { _, action, _ ->
        when (action) {
            is FavoritesAction.Init -> initialize()
            is FavoritesAction.Remove -> removeJoke(action.data)
            else -> Unit
        }
    }

    override fun middleware() = applyMiddleware(viewModelScope, thunk)

    init {
        dispatch(FavoritesAction.Init)
    }

    private suspend fun initialize() {
        getFavoriteJokes().collect {
            when (it) {
                is Outcome.Success -> dispatch(FavoritesAction.Update(it.data))
                is Outcome.Failure -> dispatch(FavoritesAction.Error(it.error))
            }
        }
    }

    private suspend fun removeJoke(joke: Joke) {
        when (val res = removeFavoriteJoke(joke)) {
            is Outcome.Failure -> dispatch(FavoritesAction.Error(res.error))
            is Outcome.Success -> Unit
        }
    }
}
