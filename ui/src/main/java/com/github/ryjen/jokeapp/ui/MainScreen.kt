package com.github.ryjen.jokeapp.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.ryjen.jokeapp.ui.components.NavBottomBar
import com.github.ryjen.jokeapp.ui.components.NavTopBar
import com.github.ryjen.jokeapp.ui.components.NotificationHost
import com.github.ryjen.jokeapp.ui.navigation.NavGraph
import com.github.ryjen.jokeapp.ui.navigation.rememberRouter
import com.github.ryjen.jokeapp.ui.theme.MainTheme
import com.github.ryjen.jokeapp.ui.theme.ThemeColors
import com.google.accompanist.insets.ProvideWindowInsets

@Composable
fun MainScreen() {
    ProvideWindowInsets {
        MainTheme {

            val scaffold = rememberScaffoldState()

            val router = rememberRouter(scaffold = scaffold)

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
