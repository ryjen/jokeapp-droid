package com.github.ryjen.jokeapp.domain.repository.joke.source

import com.github.ryjen.jokeapp.domain.repository.joke.io.AsyncJokeRepository
import com.github.ryjen.jokeapp.domain.repository.joke.io.ObservableJokeRepository

interface LocalJokeRepository : AsyncJokeRepository, ObservableJokeRepository
