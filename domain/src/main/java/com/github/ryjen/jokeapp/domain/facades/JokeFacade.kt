package com.github.ryjen.jokeapp.domain.facades

import com.github.ryjen.jokeapp.domain.arch.Outcome
import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.domain.usecase.AddFavoriteJoke
import com.github.ryjen.jokeapp.domain.usecase.GetFavoriteJokes
import com.github.ryjen.jokeapp.domain.usecase.GetRandomJoke
import com.github.ryjen.jokeapp.domain.usecase.RemoveFavoriteJoke
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.firstOrNull

class JokeFacade(
    private val getRandomJoke: GetRandomJoke,
    private val addFavoriteJoke: AddFavoriteJoke,
    private val getFavoriteJokes: GetFavoriteJokes,
    private val removeFavoriteJoke: RemoveFavoriteJoke,
) {

    suspend fun favorites(collector: FlowCollector<Outcome<List<Joke>>>) =
        getFavoriteJokes().collect(collector)

    suspend fun removeFavorite(joke: Joke) = removeFavoriteJoke(joke)

    suspend fun addFavorite(joke: Joke) = addFavoriteJoke(joke)

    suspend fun random() = getRandomJoke().firstOrNull()

    suspend fun randomize(collector: FlowCollector<Outcome<Joke>>) =
        getRandomJoke().collect(collector)
}
