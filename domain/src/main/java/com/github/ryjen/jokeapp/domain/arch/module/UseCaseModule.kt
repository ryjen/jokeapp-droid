package com.github.ryjen.jokeapp.domain.arch.module

import com.github.ryjen.jokeapp.domain.facades.JokeFacade
import com.github.ryjen.jokeapp.domain.usecase.*
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

internal val useCaseModule = module {

    factory { GetNetworkAvailability(androidContext()) }

    factory { GetUserLocale() }

    factory { GetRandomJoke(get(), get()) }

    factory { GetFavoriteJokes(get()) }

    factory { AddFavoriteJoke(get()) }

    factory { RemoveFavoriteJoke(get()) }

    factory { JokeFacade(get(), get(), get(), get()) }
}
