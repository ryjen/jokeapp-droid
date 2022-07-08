package com.github.ryjen.jokeapp.data.mapping

import com.github.ryjen.jokeapp.data.model.JokeResponse
import com.github.ryjen.jokeapp.data.storage.Joke
import com.github.ryjen.jokeapp.domain.model.Joke as DomainJoke

object JokeMapper {
    operator fun invoke(input: Joke) = with(input) {
        DomainJoke(
            id = key, content = content, created = created, isFavorite = isFavorite
        )
    }

    operator fun invoke(input: DomainJoke) = with(input) {
        Joke(id = 0, key = id, content = content, created = created, isFavorite = isFavorite)
    }

    operator fun invoke(input: JokeResponse) = with(input) {
        DomainJoke(
            id = id,
            content = joke
        )
    }
}
