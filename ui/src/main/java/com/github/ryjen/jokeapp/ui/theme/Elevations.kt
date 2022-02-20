package com.github.ryjen.jokeapp.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Elevation values that can be themed.
 */
@Immutable
data class Elevations(val card: Dp = 0.dp)

@Immutable
data class Dimensions(
    val touchable: Dp = 48.dp,
    val navIcon: Dp = 24.dp,
)

internal val LocalElevations = staticCompositionLocalOf { Elevations() }

internal val LocalDimensions = staticCompositionLocalOf { Dimensions() }
