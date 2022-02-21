package com.github.ryjen.jokeapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun MainTheme(
    content: @Composable () -> Unit
) {
    val systemUi = rememberSystemUiController()

    val darkTheme = isSystemInDarkTheme()

    val colors = getThemeColors(darkTheme)

    val dimensions = getThemeDimensions(darkTheme)

    val typography = LocalTypography.current

    val shapes = LocalShapes.current

    val images = LocalImages.current

    systemUi.setSystemBarsColor(color = colors.statusBar)

    CompositionLocalProvider(
        LocalDimensions provides dimensions,
        LocalImages provides images,
        LocalColors provides colors,
        LocalShapes provides shapes,
        LocalTypography provides typography
    ) {
        MaterialTheme(
            colors = colors.material,
            typography = typography.material,
            shapes = shapes.material,
            content = content
        )
    }
}

val ThemeColors: ColorTheme @Composable get() = LocalColors.current

val ThemeTypography: TypographyTheme @Composable get() = LocalTypography.current

val ThemeShapes: ShapeTheme @Composable get() = LocalShapes.current

val ThemeImages: ImageTheme @Composable get() = LocalImages.current

val ThemeDimensions: DimensionsTheme @Composable get() = LocalDimensions.current


