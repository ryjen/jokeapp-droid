package com.github.ryjen.jokeapp.ui.jokes.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ryjen.jokeapp.domain.arch.Outcome
import com.github.ryjen.jokeapp.domain.arch.redux.*
import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.domain.usecase.AddFavoriteJoke
import com.github.ryjen.jokeapp.domain.usecase.GetFavoriteJokes
import com.github.ryjen.jokeapp.domain.usecase.RemoveFavoriteJoke
import com.github.ryjen.jokeapp.ui.arch.redux.ReduxActionWithError
import com.github.ryjen.jokeapp.ui.arch.redux.ReduxReducerWithError
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val getFavoriteJokes: GetFavoriteJokes,
    private val addFavoriteJoke: AddFavoriteJoke,
    private val removeFavoriteJoke: RemoveFavoriteJoke
) : ViewModel(), ReduxActionHandler<FavoritesActions> {

    private val reducer: ReduxReducer<FavoritesState, FavoritesActions> = { state, action ->
        when (action) {
            is FavoritesActions.Init ->
                state.copy(jokes = action.jokes)
            is FavoritesActions.Add -> {
                addJoke(action.joke)
                state
            }
            is FavoritesActions.Remove -> {
                removeJoke(action.joke)
                state
            }
        }
    }

    private val errorHandler: ReduxReducerWithError<FavoritesState> = { state, action ->
        state.copy(error = action.error)
    }

    private val store = ReduxStore(FavoritesState(), combineReducers(reducer, errorHandler))

    val state = store.stateAsStateFlow()

    init {
        viewModelScope.launch {
            getFavoriteJokes().collect {
                when (it) {
                    is Outcome.Success -> store.dispatch(FavoritesActions.Init(it.data))
                    is Outcome.Failure -> store.dispatch(FavoritesActions.Error(it.error))
                    is Outcome.Loading -> {}
                }
            }
        }
    }

    override fun onAction(action: FavoritesActions) {
        store.dispatch(action)
    }

    private fun addJoke(joke: Joke) {
        viewModelScope.launch {
            when (val res = addFavoriteJoke(joke)) {
                is Outcome.Failure -> store.dispatch(FavoritesActions.Error(res.error))
                else -> Unit
            }
        }
    }

    private fun removeJoke(joke: Joke) {
        viewModelScope.launch {
            when (val res = removeFavoriteJoke(joke)) {
                is Outcome.Failure -> store.dispatch(FavoritesActions.Error(res.error))
                else -> Unit
            }
        }
    }
}
