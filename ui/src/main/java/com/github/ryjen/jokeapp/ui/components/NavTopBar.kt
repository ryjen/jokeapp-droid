package com.github.ryjen.jokeapp.ui.components

import androidx.compose.runtime.Composable
import com.github.ryjen.jokeapp.ui.navigation.Router
import com.github.ryjen.jokeapp.ui.navigation.Routes

@Composable
fun NavTopBar(
    router: Router
) {
    val currentRoute = router.currentRoute() ?: Routes.RANDOM_JOKE

    router.navItems[currentRoute]?.menu
}
