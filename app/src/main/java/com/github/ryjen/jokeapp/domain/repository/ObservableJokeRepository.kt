package com.github.ryjen.jokeapp.domain.repository

import com.github.ryjen.jokeapp.domain.model.Joke
import kotlinx.coroutines.flow.Flow

interface ObservableJokeRepository {
    fun getFavoriteJokes(): Flow<List<Joke>>
    fun observeRandomJoke(): Flow<Joke>
}
