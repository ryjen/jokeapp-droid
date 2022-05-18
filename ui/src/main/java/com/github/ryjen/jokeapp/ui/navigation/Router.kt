package com.github.ryjen.jokeapp.ui.navigation

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.animation.ExperimentalAnimationApi
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

private val sharedNotifications = MutableStateFlow<Notification?>(null)

@ExperimentalAnimationApi
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
    private val snackBar: SnackbarHostState,
    val defaultRoute: String = Routes.RANDOM_JOKE
) {

    val navItems: Map<String, NavItem<*>> by lazy {
        routedNavItems.mapValues {
            it.value(this)
        }
    }

    val navTabs: Map<String, NavTab> = routedNavTabs

    @Composable
    fun currentNavItem() = navItems[currentRoute()]

    @Composable
    fun currentRoute(): String {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        return navBackStackEntry?.destination?.route ?: defaultRoute
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
