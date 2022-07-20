package com.github.ryjen.jokeapp.ui.jokes.favourites

import com.github.ryjen.jokeapp.domain.arch.Outcome
import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.domain.usecase.GetFavoriteJokes
import com.github.ryjen.jokeapp.domain.usecase.RemoveFavoriteJoke
import kotlinx.coroutines.flow.FlowCollector

class FavoriteJokesFacade(
    private val getFavoriteJokes: GetFavoriteJokes,
    private val removeFavoriteJoke: RemoveFavoriteJoke,
) {

    suspend fun initialize(collector: FlowCollector<Outcome<List<Joke>>>) =
        getFavoriteJokes().collect(collector)

    suspend fun removeJoke(joke: Joke) = removeFavoriteJoke(joke)
}
