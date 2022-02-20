package com.github.ryjen.jokeapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavGraph(
    router: Router,
    modifier: Modifier = Modifier,
    startDestination: String = Routes.RANDOM_JOKE
) {
    NavHost(
        navController = router.navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        for (navItem in navItems) {
            composable(
                route = navItem.key,
            ) {
                navItem.value.screen
            }
        }
    }
}
