package com.github.ryjen.jokeapp.ui.arch.module

import com.github.ryjen.jokeapp.ui.jokes.favourites.FavoritesViewModel
import com.github.ryjen.jokeapp.ui.jokes.random.RandomJokeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val viewModelModule = module {
    viewModel {
        FavoritesViewModel(get(), get())
    }
    viewModel { params ->
        RandomJokeViewModel(get(), get(), get(), params.get())
    }
}
