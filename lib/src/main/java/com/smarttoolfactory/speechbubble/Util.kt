package com.smarttoolfactory.speechbubble

import androidx.compose.ui.graphics.Color

internal fun Color.darkenColor(factor: Float): Color {

    val colorFactor = factor.coerceAtMost(1f)
    return copy(
        alpha,
        red * colorFactor,
        green * colorFactor,
        blue * colorFactor
    )
}
