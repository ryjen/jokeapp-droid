package com.github.ryjen.jokeapp.domain.repository.joke.io

import com.github.ryjen.jokeapp.domain.model.Joke

interface SyncJokeRepository {
    fun getJoke(jokeId: String): Joke?
}
