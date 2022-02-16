package com.github.ryjen.jokeapp.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.github.ryjen.jokeapp.domain.usecase.GetUserLocale
import com.github.ryjen.jokeapp.ui.components.NotificationHost
import com.github.ryjen.jokeapp.ui.navigation.*
import com.github.ryjen.jokeapp.ui.theme.BlueTheme
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.navigationBarsPadding
import org.koin.androidx.compose.inject

@Composable
fun MainScreen() {
    ProvideWindowInsets {
        BlueTheme {

            val navController = rememberNavController()
            val scaffold = rememberScaffoldState()

            val snackBar = scaffold.snackbarHostState
            val router = Router(navController, snackBar)

            Scaffold(
                scaffoldState = scaffold,
                snackbarHost = { snackBar },
                topBar = {
                    AppTopBar(router = router)
                },
                backgroundColor = MaterialTheme.colors.primarySurface,
                bottomBar = {
                    AppBottomBar(router = router)
                }
            ) { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)) {
                    NavGraph(router = router)
                    NotificationHost(host = snackBar)
                }
            }
        }
    }
}

@Composable
fun AppTopBar(
    router: Router
) {
    val currentRoute = router.currentRoute() ?: Routes.RANDOM_JOKE

    Menus[currentRoute]?.let { it() }
}

@Composable
fun AppBottomBar(
    router: Router
) {
    val getUserLocale: GetUserLocale by inject()

    val currentRoute = router.currentRoute() ?: Routes.RANDOM_JOKE

    BottomNavigation(
        Modifier.navigationBarsHeight(additional = 56.dp)
    ) {
        Tabs.forEach { (route, tab) ->
            BottomNavigationItem(
                icon = {
                    Icon(painterResource(tab.icon), contentDescription = null)
                },
                label = {
                    Text(stringResource(tab.title).uppercase(getUserLocale()))
                },
                selected = currentRoute == route,
                onClick = {
                    router.routeTo(route)
                },
                alwaysShowLabel = false,
                selectedContentColor = MaterialTheme.colors.secondary,
                unselectedContentColor = LocalContentColor.current,
                modifier = Modifier.navigationBarsPadding()
            )
        }
    }
}
