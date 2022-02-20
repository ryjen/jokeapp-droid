package com.github.ryjen.jokeapp.domain.repository.joke.io

import com.github.ryjen.jokeapp.domain.model.Joke
import kotlinx.coroutines.flow.Flow

interface ObservableJokeRepository {
    fun getFavoriteJokes(): Flow<List<Joke>>
}
