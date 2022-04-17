package com.github.ryjen.jokeapp.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

// a navigation tab
data class NavTab(
    @StringRes val title: Int,
    val icon: @Composable () -> ImageVector
)
