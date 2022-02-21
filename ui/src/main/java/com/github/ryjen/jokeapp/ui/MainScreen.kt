package com.github.ryjen.jokeapp.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.github.ryjen.jokeapp.ui.components.NavBottomBar
import com.github.ryjen.jokeapp.ui.components.NavTopBar
import com.github.ryjen.jokeapp.ui.components.NotificationHost
import com.github.ryjen.jokeapp.ui.navigation.NavGraph
import com.github.ryjen.jokeapp.ui.navigation.Router
import com.github.ryjen.jokeapp.ui.theme.MainTheme
import com.github.ryjen.jokeapp.ui.theme.ThemeColors
import com.google.accompanist.insets.ProvideWindowInsets

@Composable
fun MainScreen() {
    val context = LocalContext.current

    ProvideWindowInsets {
        MainTheme {

            val navController = rememberNavController()
            val scaffold = rememberScaffoldState()

            val router = Router(context, navController, scaffold.snackbarHostState)

            Scaffold(
                scaffoldState = scaffold,
                snackbarHost = { NotificationHost(host = it, router) },
                topBar = {
                    NavTopBar(router = router)
                },
                backgroundColor = ThemeColors.material.background,
                contentColor = ThemeColors.material.onBackground,
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
