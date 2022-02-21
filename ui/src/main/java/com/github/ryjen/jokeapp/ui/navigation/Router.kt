package com.github.ryjen.jokeapp.ui.navigation

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.github.ryjen.jokeapp.ui.components.Notifications
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

object Routes {
    const val RANDOM_JOKE = "jokes/random"
    const val FAVORITE_JOKES = "jokes/favorites"
}

private val sharedNotifications = MutableStateFlow<Notifications?>(null)

class Router(
    private val context: Context,
    val navController: NavHostController,
    private val snackBar: SnackbarHostState
) {

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

    fun notifications() = sharedNotifications.asStateFlow()

    suspend fun show(value: Notifications) {
        sharedNotifications.emit(value)
        snackBar.showSnackbar(value.message, value.action?.label)
    }

    suspend fun showSuccess(@StringRes value: Int, vararg args: Any) =
        show(
            Notifications.Success(
                context.getString(value, *args)
            )
        )


    suspend fun showDanger(@StringRes value: Int, vararg args: Any) =
        show(
            Notifications.Danger(
                context.getString(value, *args)
            )
        )
}
