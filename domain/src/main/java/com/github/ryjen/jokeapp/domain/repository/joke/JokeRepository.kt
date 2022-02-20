package com.github.ryjen.jokeapp.domain.repository.joke

import com.github.ryjen.jokeapp.domain.repository.joke.io.AsyncJokeRepository
import com.github.ryjen.jokeapp.domain.repository.joke.io.ObservableJokeRepository
import com.github.ryjen.jokeapp.domain.repository.joke.io.SyncJokeRepository
import com.github.ryjen.jokeapp.domain.repository.joke.source.LocalJokeRepository
import com.github.ryjen.jokeapp.domain.repository.joke.source.RemoteJokeRepository

interface JokeRepository {
    val sync: SyncJokeRepository
    val async: AsyncJokeRepository
    val observable: ObservableJokeRepository
    val remote: RemoteJokeRepository
    val local: LocalJokeRepository
}
