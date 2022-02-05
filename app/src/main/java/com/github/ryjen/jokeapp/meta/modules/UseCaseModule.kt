package com.github.ryjen.jokeapp.meta.modules

import com.github.ryjen.jokeapp.domain.usecase.*
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

internal val useCaseModule = module {

    factory { GetNetworkAvailability(androidContext()) }

    factory { GetUserLocale() }

    factory { GetFavoriteJokes(get(), get()) }

    factory { AddFavoriteJoke(get(), get())}

    factory { RemoveFavoriteJoke(get(), get()) }
}
