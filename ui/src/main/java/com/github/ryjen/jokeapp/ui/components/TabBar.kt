package com.github.ryjen.jokeapp.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.ryjen.jokeapp.domain.usecase.GetUserLocale
import com.github.ryjen.jokeapp.ui.navigation.Router
import com.github.ryjen.jokeapp.ui.navigation.Routes
import com.github.ryjen.jokeapp.ui.theme.ThemeColors
import com.github.ryjen.jokeapp.ui.theme.ThemeDimensions
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.navigationBarsPadding
import org.koin.androidx.compose.inject

/*
  Use in footers to get bottom navigation
 */

@Composable
fun TabBar(router: Router) {
    val currentRoute = router.currentRoute() ?: Routes.RANDOM_JOKE

    val getUserLocale: GetUserLocale by inject()

    BottomNavigation(
        Modifier.navigationBarsHeight(additional = 56.dp),
        backgroundColor = ThemeColors.bottomBar,
        contentColor = ThemeColors.onBottomBar
    ) {
        router.navTabs.forEach { (route, tab) ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = tab.icon(),
                        modifier = Modifier.size(ThemeDimensions.icons.small),
                        contentDescription = null
                    )
                },
                label = {
                    Text(stringResource(tab.title).uppercase(getUserLocale()))
                },
                selected = currentRoute == route,
                onClick = {
                    router.routeTo(route)
                },
                alwaysShowLabel = true,
                selectedContentColor = ThemeColors.material.secondary,
                unselectedContentColor = LocalContentColor.current,
                modifier = Modifier.navigationBarsPadding()
            )
        }
    }
}
