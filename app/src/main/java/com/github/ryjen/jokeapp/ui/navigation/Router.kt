package com.github.ryjen.jokeapp.ui.navigation

import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

internal object Routes {
    const val RANDOM_JOKE = "jokes/random"
    const val FAVORITE_JOKES = "jokes/favorites"
}

class Router(val navController: NavHostController, private val snackBar: SnackbarHostState) {

    @Composable
    fun currentRoute(): String? {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        return navBackStackEntry?.destination?.route
    }

    fun routeTo(value: String) {
        navController.navigate(value) {
            popUpTo(navController.graph.startDestinationId) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}
