package com.github.ryjen.jokeapp.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.primarySurface
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
import com.github.ryjen.jokeapp.ui.theme.BlueTheme
import com.google.accompanist.insets.ProvideWindowInsets

@Composable
fun MainScreen() {
    val context = LocalContext.current

    ProvideWindowInsets {
        BlueTheme {

            val navController = rememberNavController()
            val scaffold = rememberScaffoldState()

            val router = Router(context, navController)

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
