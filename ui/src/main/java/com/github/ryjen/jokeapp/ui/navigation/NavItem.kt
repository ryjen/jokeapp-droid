package com.github.ryjen.jokeapp.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import com.github.ryjen.jokeapp.ui.R
import com.github.ryjen.jokeapp.ui.jokes.favourites.FavoritesMenu
import com.github.ryjen.jokeapp.ui.jokes.favourites.FavoritesScreen
import com.github.ryjen.jokeapp.ui.jokes.favourites.FavoritesViewModel
import com.github.ryjen.jokeapp.ui.jokes.random.RandomJokeMenu
import com.github.ryjen.jokeapp.ui.jokes.random.RandomJokeScreen
import com.github.ryjen.jokeapp.ui.jokes.random.RandomJokeViewModel
import com.github.ryjen.jokeapp.ui.theme.AppTheme
import org.koin.androidx.compose.getViewModel

data class Tab(
    @StringRes val title: Int,
    val icon: ImageVector
)

data class NavItem<T : ViewModel>(
    private val onTab: @Composable () -> Tab,
    private val onData: @Composable () -> T,
    private val onMenu: @Composable (T) -> Unit,
    private val onScreen: @Composable (T) -> Unit,
) {
    private val data = CachedComposable(onData)
    val screen @Composable get() = onScreen(data())
    val menu @Composable get() = onMenu(data())
    val tab @Composable get() = onTab()
}

fun randomJokeNavItem(): NavItem<RandomJokeViewModel> {
    return NavItem(
        { Tab(R.string.action_random, AppTheme.images.refresh) },
        { getViewModel() },
        { viewModel -> RandomJokeMenu(viewModel) },
        { viewModel -> RandomJokeScreen(viewModel) },
    )
}

fun favoritesNavItem(): NavItem<FavoritesViewModel> {
    return NavItem(
        { Tab(R.string.action_favorites, AppTheme.images.bookmarks) },
        { getViewModel() },
        { viewModel -> FavoritesMenu(viewModel) },
        { viewModel -> FavoritesScreen(viewModel) }
    )
}

class CachedComposable<T : ViewModel>(
    private val builder: @Composable () -> T
) {
    private var item: T? = null

    @Composable
    operator fun invoke(): T {
        if (item == null) {
            item = builder()
        }
        return item!!
    }
}

val navItems = mapOf(
    Pair(Routes.RANDOM_JOKE, randomJokeNavItem()),
    Pair(Routes.FAVORITE_JOKES, favoritesNavItem()),
)