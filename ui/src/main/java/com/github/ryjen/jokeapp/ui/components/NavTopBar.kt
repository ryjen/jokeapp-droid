package com.github.ryjen.jokeapp.ui.components

import androidx.compose.runtime.Composable
import com.github.ryjen.jokeapp.ui.navigation.Router
import com.github.ryjen.jokeapp.ui.navigation.Routes
import com.github.ryjen.jokeapp.ui.navigation.navItems

@Composable
fun NavTopBar(
    router: Router
) {
    val currentRoute = router.currentRoute() ?: Routes.RANDOM_JOKE

    navItems[currentRoute]?.menu
}