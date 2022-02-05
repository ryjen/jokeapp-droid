package com.github.ryjen.jokeapp.meta.modules

import com.github.ryjen.jokeapp.ui.jokes.favourites.FavoritesViewModel
import com.github.ryjen.jokeapp.ui.jokes.random.JokeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val viewModelModule = module {
    viewModel {
        FavoritesViewModel(get(), get(), get())
    }
    viewModel {
        JokeViewModel(get())
    }
}
