package com.github.ryjen.jokeapp.ui.theme

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

private val shapes = Shapes(
    small = RoundedCornerShape(percent = 50),
    medium = RoundedCornerShape(size = 0f),
    large = RoundedCornerShape(
        topStart = 16.dp,
        topEnd = 0.dp,
        bottomEnd = 0.dp,
        bottomStart = 16.dp
    )
)

@Immutable
data class ShapeTheme(
    val material: Shapes = shapes,
    val bubble: CornerBasedShape = RoundedCornerShape(12.dp)
)

internal val LocalShapes = staticCompositionLocalOf { ShapeTheme() }
