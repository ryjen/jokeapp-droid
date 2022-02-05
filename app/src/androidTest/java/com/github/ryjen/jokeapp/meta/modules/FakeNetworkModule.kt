package com.github.ryjen.jokeapp.meta.modules

import com.github.ryjen.jokeapp.data.api.FakeJokeService
import org.koin.dsl.module

internal val fakeNetworkModule = module {

    single {
        FakeJokeService()
    }

    single {
        get<FakeJokeService>().client()
    }
}
