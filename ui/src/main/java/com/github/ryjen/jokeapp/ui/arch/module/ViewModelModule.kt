package com.github.ryjen.jokeapp.ui.arch.module

import com.github.ryjen.jokeapp.ui.jokes.favourites.FavoriteJokesFacade
import com.github.ryjen.jokeapp.ui.jokes.favourites.FavoritesViewModel
import com.github.ryjen.jokeapp.ui.jokes.random.RandomJokeFacade
import com.github.ryjen.jokeapp.ui.jokes.random.RandomJokeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val viewModelModule = module {

    factory {
        FavoriteJokesFacade(get(), get())
    }
    factory {
        RandomJokeFacade(get(), get(), get())
    }

    viewModel {
        FavoritesViewModel(get())
    }
    viewModel { params ->
        RandomJokeViewModel(params.get(), get())
    }
}
