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
import com.github.ryjen.jokeapp.ui.theme.AppTheme
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.navigationBarsPadding
import org.koin.androidx.compose.inject

@Composable
fun NavBottomBar(
    router: Router
) {
    val getUserLocale: GetUserLocale by inject()

    val currentRoute = router.currentRoute() ?: Routes.RANDOM_JOKE

    BottomNavigation(
        Modifier.navigationBarsHeight(additional = 56.dp)
    ) {
        router.navItems.forEach { (route, nav) ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = nav.tab.icon,
                        modifier = Modifier.size(AppTheme.dimens.navIcon),
                        contentDescription = null
                    )
                },
                label = {
                    Text(stringResource(nav.tab.title).uppercase(getUserLocale()))
                },
                selected = currentRoute == route,
                onClick = {
                    router.routeTo(route)
                },
                alwaysShowLabel = true,
                selectedContentColor = AppTheme.colors.secondary,
                unselectedContentColor = LocalContentColor.current,
                modifier = Modifier.navigationBarsPadding()
            )
        }
    }
}
