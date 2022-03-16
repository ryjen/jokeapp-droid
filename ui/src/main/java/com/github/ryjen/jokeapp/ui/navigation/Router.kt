package com.github.ryjen.jokeapp.ui.navigation

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.github.ryjen.jokeapp.ui.arch.Notification
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

object Routes {
    const val RANDOM_JOKE = "jokes/random"
    const val FAVORITE_JOKES = "jokes/favorites"
}

private val sharedNotifications = MutableStateFlow<Notification?>(null)

@Composable
fun rememberRouter(
    context: Context = LocalContext.current,
    navController: NavHostController = rememberNavController(),
    scaffold: ScaffoldState = rememberScaffoldState()
) = remember {
    Router(context, navController, scaffold.snackbarHostState)
}

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

    suspend fun show(value: Notification) {
        sharedNotifications.emit(value)
        snackBar.currentSnackbarData?.dismiss()
        snackBar.showSnackbar(value.message, value.action?.label)
    }

    suspend fun showSuccess(@StringRes value: Int, vararg args: Any) =
        show(
            Notification.Success(
                context.getString(value, *args)
            )
        )

    suspend fun showWarning(@StringRes value: Int, vararg args: Any) =
        show(
            Notification.Warn(
                context.getString(value, *args)
            )
        )

    suspend fun showInfo(@StringRes value: Int, vararg args: Any) =
        show(
            Notification.Info(
                context.getString(value, *args)
            )
        )

    suspend fun showDanger(@StringRes value: Int, vararg args: Any) =
        show(
            Notification.Danger(
                context.getString(value, *args)
            )
        )
}
