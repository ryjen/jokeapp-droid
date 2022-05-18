package com.github.ryjen.jokeapp.data.arch.module

import com.github.ryjen.jokeapp.data.api.JokeService
import com.github.ryjen.jokeapp.data.api.createJokeClient
import org.koin.dsl.module

internal val networkSourceModule = module {

    single {
        createJokeClient()
    }

    single {
        JokeService(get())
    }
}
