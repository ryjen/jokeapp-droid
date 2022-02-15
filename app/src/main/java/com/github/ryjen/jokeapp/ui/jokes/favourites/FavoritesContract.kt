package com.github.ryjen.jokeapp.ui.jokes.favourites

import com.github.ryjen.jokeapp.domain.arch.redux.*
import com.github.ryjen.jokeapp.ui.arch.Failure
import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.ui.arch.redux.ReduxActionWithErrorCompanion
import com.github.ryjen.jokeapp.ui.arch.redux.ReduxStateWithError

data class FavoritesState(
    val jokes: List<Joke> = listOf(),
    override val error: Failure? = null
) : ReduxStateWithError

sealed class FavoritesActions: ReduxAction {
    companion object: ReduxActionWithErrorCompanion
    data class Init(val jokes: List<Joke>): FavoritesActions()
    data class Add(val joke: Joke) : FavoritesActions()
    data class Remove(val joke: Joke) : FavoritesActions()
}
