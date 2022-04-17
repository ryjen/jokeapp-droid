package com.github.ryjen.jokeapp.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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

            val navItem = router.currentNavItem()

            Scaffold(
                scaffoldState = scaffold,
                snackbarHost = { NotificationHost(host = it, router) },
                topBar = {
                    navItem?.header
                },
                backgroundColor = ThemeColors.material.background,
                contentColor = ThemeColors.material.onBackground,
                bottomBar = {
                    navItem?.footer
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
