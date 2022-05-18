package com.github.ryjen.jokeapp.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@ExperimentalAnimationApi
@Composable
fun NavGraph(
    router: Router,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = router.navController,
        startDestination = router.defaultRoute,
        modifier = modifier
    ) {
        for (navItem in router.navItems) {
            composable(
                route = navItem.key
            ) {
                navItem.value.screen
            }
        }
    }
}
