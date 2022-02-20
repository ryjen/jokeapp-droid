package com.github.ryjen.jokeapp.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.github.ryjen.jokeapp.domain.usecase.GetUserLocale
import com.github.ryjen.jokeapp.ui.components.NavBottomBar
import com.github.ryjen.jokeapp.ui.components.NavTopBar
import com.github.ryjen.jokeapp.ui.components.NotificationHost
import com.github.ryjen.jokeapp.ui.navigation.NavGraph
import com.github.ryjen.jokeapp.ui.navigation.Router
import com.github.ryjen.jokeapp.ui.navigation.Routes
import com.github.ryjen.jokeapp.ui.navigation.navItems
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

            val router = Router(navController)

            Scaffold(
                scaffoldState = scaffold,
                snackbarHost = { NotificationHost(host = it, router) },
                topBar = {
                    NavTopBar(router = router)
                },
                backgroundColor = MaterialTheme.colors.primarySurface,
                bottomBar = {
                    NavBottomBar(router = router)
                }
            ) { innerPadding ->
                NavGraph(
                    router = router,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}
