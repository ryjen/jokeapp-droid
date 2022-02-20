package com.github.ryjen.jokeapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.github.ryjen.jokeapp.ui.components.Notifications
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object Routes {
    const val RANDOM_JOKE = "jokes/random"
    const val FAVORITE_JOKES = "jokes/favorites"
}

private val sharedNotifications = MutableSharedFlow<Notifications>()

class Router(val navController: NavHostController) {

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

    fun notifications() = sharedNotifications.asSharedFlow()

    suspend fun showNotification(value: Notifications) {
        sharedNotifications.emit(value)
    }
}
