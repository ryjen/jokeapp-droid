package com.github.ryjen.jokeapp.data.mapping

import com.github.ryjen.jokeapp.data.model.Joke
import com.github.ryjen.jokeapp.domain.mapping.JokeMapper as DomainMapper
import com.github.ryjen.jokeapp.domain.model.Joke as DomainJoke

object JokeMapper : DomainMapper<Joke> {
    override fun invoke(input: Joke) = with(input) {
        DomainJoke(
            id = id, content = content, created = created, isFavorite = isFavorite
        )
    }

    override fun invoke(input: DomainJoke) = with(input) {
        Joke(id = id, content = content, created = created, isFavorite = isFavorite)
    }
}
