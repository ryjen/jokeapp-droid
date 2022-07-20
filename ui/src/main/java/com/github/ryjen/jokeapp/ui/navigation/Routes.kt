package com.github.ryjen.jokeapp.ui.navigation

import com.github.ryjen.jokeapp.ui.R
import com.github.ryjen.jokeapp.ui.jokes.favourites.FavoritesFooter
import com.github.ryjen.jokeapp.ui.jokes.favourites.FavoritesHeader
import com.github.ryjen.jokeapp.ui.jokes.favourites.FavoritesScreen
import com.github.ryjen.jokeapp.ui.jokes.favourites.FavoritesViewModel
import com.github.ryjen.jokeapp.ui.jokes.random.RandomJokeFooter
import com.github.ryjen.jokeapp.ui.jokes.random.RandomJokeHeader
import com.github.ryjen.jokeapp.ui.jokes.random.RandomJokeScreen
import com.github.ryjen.jokeapp.ui.jokes.random.RandomJokeViewModel
import com.github.ryjen.jokeapp.ui.theme.ThemeImages
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

object Routes {
    const val RANDOM_JOKE = "jokes/random"
    const val FAVORITE_JOKES = "jokes/favorites"
}

// list of navigation tabs by route
val routedNavTabs = mapOf<String, NavTab>(
    Pair(Routes.RANDOM_JOKE,
        NavTab(
            title = R.string.action_random,
            icon = { ThemeImages.random }
        )
    ),
    Pair(Routes.FAVORITE_JOKES,
        NavTab(
            title = R.string.action_favorites,
            icon = { ThemeImages.bookmarks }
        )
    )
)

// list of navigation items by route
val routedNavItems = mapOf<String, NavItemBuilder<*>>(
    Pair(Routes.RANDOM_JOKE) { router ->
        NavItem<RandomJokeViewModel>(
            onViewModel = { getViewModel { parametersOf(router) } },
            onFooter = { RandomJokeFooter(router) },
            onScreen = { viewModel -> RandomJokeScreen(viewModel) },
            onHeader = { viewModel -> RandomJokeHeader(viewModel) },
        )
    },

    Pair(Routes.FAVORITE_JOKES) { router ->
        NavItem<FavoritesViewModel>(
            onViewModel = { getViewModel() },
            onFooter = { viewModel -> FavoritesFooter(router, viewModel) },
            onScreen = { viewModel -> FavoritesScreen(viewModel) },
            onHeader = { FavoritesHeader() },
        )
    }
)
