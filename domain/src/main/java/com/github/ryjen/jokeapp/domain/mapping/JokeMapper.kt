package com.github.ryjen.jokeapp.domain.mapping

import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.domain.model.JokeResponse

interface JokeMapper<T> {
    operator fun invoke(input: T): Joke
    operator fun invoke(input: Joke): T
    operator fun invoke(input: JokeResponse) = with(input) {
        Joke(
            id = id,
            content = joke
        )
    }
}
