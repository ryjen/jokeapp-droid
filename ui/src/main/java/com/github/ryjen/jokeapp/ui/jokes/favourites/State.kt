package com.github.ryjen.jokeapp.ui.jokes.favourites

import com.github.ryjen.jokeapp.domain.arch.redux.ReduxState
import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.ui.arch.Failure
import com.github.ryjen.jokeapp.ui.arch.ViewState

data class FavoritesState(
    val jokes: List<Joke> = listOf(),
    val error: Failure? = null
) : ReduxState

data class FavoritesViewState(
    val jokes: List<Joke> = listOf(),
    val error: Failure? = null
) : ViewState
