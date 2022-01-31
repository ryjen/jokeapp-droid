package com.github.ryjen.jokeapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.github.ryjen.jokeapp.ui.jokes.favourites.FavoritesScreen
import com.github.ryjen.jokeapp.ui.jokes.random.JokeScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String = Routes.RANDOM_JOKE
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(
            route = Routes.RANDOM_JOKE,
        ) {
            JokeScreen()
        }
        composable(route = Routes.FAVORITE_JOKES) {
            FavoritesScreen()
        }
    }
}
