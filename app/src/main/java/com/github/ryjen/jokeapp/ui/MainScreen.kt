package com.github.ryjen.jokeapp.ui

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.github.ryjen.jokeapp.ui.navigation.Menus
import com.github.ryjen.jokeapp.ui.navigation.NavGraph
import com.github.ryjen.jokeapp.ui.navigation.Routes
import com.github.ryjen.jokeapp.ui.navigation.Tabs
import com.github.ryjen.jokeapp.ui.theme.BlueTheme
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.navigationBarsPadding

@Composable
fun MainScreen() {
    ProvideWindowInsets {
        BlueTheme {

            val navController = rememberNavController()

            Scaffold(
                topBar = {
                    AppTopBar(navController = navController)
                },
                backgroundColor = MaterialTheme.colors.primarySurface,
                bottomBar = {
                    AppBottomBar(navController = navController)
                }
            ) {
                NavGraph(navController = navController)
            }
        }
    }
}

@Composable
fun AppTopBar(
    navController: NavController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
        ?: Routes.RANDOM_JOKE

    Menus[currentRoute]
}

@Composable
fun AppBottomBar(
    viewModel: MainViewModel = viewModel(),
    navController: NavController
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
        ?: Routes.RANDOM_JOKE

    BottomNavigation(
        Modifier.navigationBarsHeight(additional = 56.dp)
    ) {
        Tabs.forEach { (route, tab) ->
            BottomNavigationItem(
                icon = {
                    Icon(painterResource(tab.icon), contentDescription = null)
                },
                label = {
                    Text(stringResource(tab.title).uppercase(viewModel.userLocale))
                },
                selected = currentRoute == route,
                onClick = {
                    if (route != currentRoute) {
                        navController.navigate(route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                alwaysShowLabel = false,
                selectedContentColor = MaterialTheme.colors.secondary,
                unselectedContentColor = LocalContentColor.current,
                modifier = Modifier.navigationBarsPadding()
            )
        }
    }
}
