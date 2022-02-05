package com.github.ryjen.jokeapp.ui.navigation

import androidx.compose.runtime.Composable
import com.github.ryjen.jokeapp.ui.jokes.favourites.FavoritesMenu
import com.github.ryjen.jokeapp.ui.jokes.random.JokeMenu

val Menus: Map<String, @Composable () -> Unit> = mapOf(
    Pair(Routes.RANDOM_JOKE) { JokeMenu() },
    Pair(Routes.FAVORITE_JOKES) { FavoritesMenu() }
)
