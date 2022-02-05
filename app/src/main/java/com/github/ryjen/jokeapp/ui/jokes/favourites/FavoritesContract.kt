package com.github.ryjen.jokeapp.ui.jokes.favourites

import com.github.ryjen.jokeapp.domain.arch.redux.*
import com.github.ryjen.jokeapp.domain.model.Failure
import com.github.ryjen.jokeapp.domain.model.Joke

data class FavoritesState(
    val jokes: List<Joke> = listOf(),
    override val error: Failure? = null
) : ErrorState

sealed class FavoritesActions: ReduxAction {
    companion object: ErrorActionCompanion
    data class Init(val jokes: List<Joke>): FavoritesActions()
    data class Add(val joke: Joke) : FavoritesActions()
    data class Remove(val joke: Joke) : FavoritesActions()
}
