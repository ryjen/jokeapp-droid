package com.github.ryjen.jokeapp.domain.repository.joke.io

import com.github.ryjen.jokeapp.domain.repository.joke.source.LocalJokeRepository
import com.github.ryjen.jokeapp.domain.repository.joke.source.RemoteJokeRepository

interface AsyncJokeRepository : LocalJokeRepository, RemoteJokeRepository
