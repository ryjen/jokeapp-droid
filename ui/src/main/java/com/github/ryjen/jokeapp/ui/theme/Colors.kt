package com.github.ryjen.jokeapp.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

private val yellowPrimary = Color(0xffE4EA8F)
private val yellowDark = Color(0xff5b5d39)
private val yellowLight = Color(0xfff1f4c7)

private val bluePrimary = Color(0xff2e6171)
private val blueDark = Color(0xff173038)
private val blueLight = Color(0xff96b0b8)

private val redPrimary = Color(0xffc05746)
private val redDark = Color(0xff602b23)
private val redLight = Color(0xffdfaba2)

private val purplePrimary = Color(0xff712E61)
private val purpleDark = Color(0xFF381730)
private val purpleLight = Color(0xFFD4C0CF)

private val greenPrimary = Color(0xff61712E)
private val greenDark = Color(0xff303817)
private val greenLight = Color(0xffB0B896)

private val tealPrimary = Color(0xFF2E7160)
private val tealLight = Color(0xFFABC6BF)
private val tealDark = Color(0xff173830)

private val whitePrimary = Color(0xffE0E2DB)
private val whiteLight = Color(0xffF2F3F0)
private val whiteDark = Color(0xffC9CBC5)

private val blackPrimary = Color(0xff161615)
private val blackLight = Color(0xff2D2D2C)
private val blackLighter = Color(0xff5b5b5b)
private val blackDark = Color(0xff000000)

const val previewBackground = 0xffF2F3F0

@Immutable
data class ColorTheme(
    val material: Colors,
    val topBar: Color,
    val onTopBar: Color,
    val bottomBar: Color,
    val onBottomBar: Color,
    val info: Color = tealDark,
    val onInfo: Color = whitePrimary,
    val success: Color = greenDark,
    val onSuccess: Color = whitePrimary,
    val warn: Color = yellowDark,
    val onWarn: Color = whitePrimary,
    val statusBar: Color,
    val card: Color,
    val cardAction: Color,
    val onCard: Color,
    val cardBorder: Color,
    val speaker: Color,
    val shadow: Color = blackLighter,
)


internal val BlueThemeLight = ColorTheme(
    material = lightColors(
        primary = bluePrimary,
        primaryVariant = purplePrimary,
        onPrimary = whitePrimary,

        secondary = yellowPrimary,
        secondaryVariant = greenPrimary,
        onSecondary = whitePrimary,

        background = whiteLight,
        onBackground = blackPrimary,

        surface = whitePrimary,
        onSurface = blackPrimary,

        error = redPrimary,
        onError = whitePrimary
    ),
    topBar = bluePrimary,
    onTopBar = whiteDark,

    bottomBar = bluePrimary,
    onBottomBar = whiteDark,

    statusBar = bluePrimary,

    card = yellowLight,
    cardAction = blackLighter,
    onCard = blackLight,
    cardBorder = yellowPrimary,

    speaker = yellowPrimary
)

internal val BlueThemeDark = ColorTheme(
    material = darkColors(
        primary = blueLight,
        primaryVariant = purpleLight,
        onPrimary = blackPrimary,

        secondary = yellowLight,
        secondaryVariant = greenLight,
        onSecondary = blackPrimary,

        background = blackPrimary,
        onBackground = whiteDark,

        surface = blackLight,
        onSurface = whiteDark,

        error = redLight,
        onError = blackPrimary
    ),
    topBar = blueDark,
    onTopBar = whiteDark,

    bottomBar = blueDark,
    onBottomBar = whiteDark,

    statusBar = blueDark,

    card = yellowDark,
    cardAction = blackDark,
    onCard = whiteDark,
    cardBorder = yellowPrimary,

    speaker = blueDark
)

internal fun getThemeColors(darkTheme: Boolean) = if (darkTheme) BlueThemeDark else BlueThemeLight

internal val LocalColors = staticCompositionLocalOf { BlueThemeLight }
