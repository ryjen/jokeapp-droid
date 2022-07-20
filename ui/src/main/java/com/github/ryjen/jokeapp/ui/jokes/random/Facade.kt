package com.github.ryjen.jokeapp.ui.jokes.random

import com.github.ryjen.jokeapp.domain.arch.Outcome
import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.domain.usecase.AddFavoriteJoke
import com.github.ryjen.jokeapp.domain.usecase.GetRandomJoke
import com.github.ryjen.jokeapp.domain.usecase.RemoveFavoriteJoke
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.firstOrNull


class RandomJokeFacade(
    private val getRandomJoke: GetRandomJoke,
    private val addFavoriteJoke: AddFavoriteJoke,
    private val removeFavoriteJoke: RemoveFavoriteJoke,
) {
    suspend fun add(joke: Joke) = addFavoriteJoke(joke)

    suspend fun remove(joke: Joke) = removeFavoriteJoke(joke)

    suspend fun refresh() = getRandomJoke().firstOrNull()

    suspend fun randomize(collector: FlowCollector<Outcome<Joke>>) =
        getRandomJoke().collect(collector)
}
