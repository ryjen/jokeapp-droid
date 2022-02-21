package com.github.ryjen.jokeapp.ui.navigation

import androidx.annotation.StringRes
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
import com.github.ryjen.jokeapp.ui.theme.ThemeImages
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

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

fun randomJokeNavItem(router: Router): NavItem<RandomJokeViewModel> {
    return NavItem(
        { Tab(R.string.action_random, ThemeImages.random) },
        {
            getViewModel {
                parametersOf(router)
            }
        },
        { viewModel -> RandomJokeMenu(viewModel) },
        { viewModel -> RandomJokeScreen(viewModel) },
    )
}

fun favoritesNavItem(): NavItem<FavoritesViewModel> {
    return NavItem(
        { Tab(R.string.action_favorites, ThemeImages.bookmarks) },
        { getViewModel() },
        { FavoritesMenu() },
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
