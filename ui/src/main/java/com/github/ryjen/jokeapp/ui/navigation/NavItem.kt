package com.github.ryjen.jokeapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import com.github.ryjen.jokeapp.ui.components.LazyComposable

// build a nav item with a router
typealias NavItemBuilder<ViewModel> = (router: Router) -> NavItem<ViewModel>

// a navigation item that represents the pieces of an entire screen
data class NavItem<VM : ViewModel>(
    private val onViewModel: @Composable () -> VM,
    private val onHeader: @Composable (VM) -> Unit,
    private val onScreen: @Composable (VM) -> Unit,
    private val onFooter: @Composable (VM) -> Unit,
) {
    private val viewModel = LazyComposable(onViewModel)

    val screen @Composable get() = onScreen(viewModel())
    val header @Composable get() = onHeader(viewModel())
    val footer @Composable get() = onFooter(viewModel())
}


