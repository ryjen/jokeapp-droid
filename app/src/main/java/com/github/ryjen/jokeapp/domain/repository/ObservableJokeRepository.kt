package com.github.ryjen.jokeapp.domain.repository

import com.github.ryjen.jokeapp.domain.model.Joke
import kotlinx.coroutines.flow.Flow

interface ObservableJokeRepository {
    suspend fun getFavouriteJokes(): Flow<Joke>
    suspend fun getRandomJoke(): Flow<Joke>
}
