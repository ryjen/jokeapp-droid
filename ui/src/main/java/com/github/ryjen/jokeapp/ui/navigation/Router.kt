package com.github.ryjen.jokeapp.ui.navigation

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
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

class Router(private val context: Context, val navController: NavHostController) {

    val navItems = mapOf(
        Pair(Routes.RANDOM_JOKE, randomJokeNavItem(this)),
        Pair(Routes.FAVORITE_JOKES, favoritesNavItem()),
    )

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

    suspend fun show(value: Notifications) {
        sharedNotifications.emit(value)
    }

    suspend fun showSuccess(@StringRes value: Int, vararg args: Any) {
        val notification = Notifications.Success(
            context.getString(value, *args)
        )
        sharedNotifications.emit(notification)
    }

    suspend fun showDanger(@StringRes value: Int, vararg args: Any) {
        val notification = Notifications.Danger(
            context.getString(value, *args)
        )
        sharedNotifications.emit(notification)
    }
}
