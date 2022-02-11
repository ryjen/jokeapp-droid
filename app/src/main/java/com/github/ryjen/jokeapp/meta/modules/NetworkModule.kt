package com.github.ryjen.jokeapp.meta.modules

import com.github.ryjen.jokeapp.data.api.JokeService
import com.github.ryjen.jokeapp.data.api.createJokeClient
import org.koin.dsl.module

internal val networkModule = module {

    single {
        createJokeClient()
    }

    single {
        JokeService(get())
    }
}
