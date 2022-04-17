package com.github.ryjen.jokeapp.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

private val yellowDark = Color(0xffFFBA08)
private val yellowLight = Color(0xffFFBA08)

private val orangeDark = Color(0xffF49D37)
private val orangeLight = Color(0xffF49D37)

private val blueDark = Color(0xff3F88C5)
private val blueLight = Color(0xff3F88C5)

private val redDark = Color(0xffD72638)
private val redLight = Color(0xffD72638)

private val purpleDark = Color(0xff712E61)
private val purpleLight = Color(0xff712E61)

private val greenDark = Color(0xff248232)
private val greenLight = Color(0xff248232)

private val tealLight = Color(0xFF538083)
private val tealDark = Color(0xff538083)

private val whiteLight = Color(0xffF2F3F0)
private val whiteDark = Color(0xffC9CBC5)

private val blackLight = Color(0xff2D2D2C)
private val blackLighter = Color(0xff5b5b5b)
private val blackDark = Color(0xff000000)

const val previewBackground = 0xffF2F3F0
private val card = Color(0xffFFEED6)

@Immutable
data class ColorTheme(
    val material: Colors,
    val topBar: Color,
    val onTopBar: Color,
    val bottomBar: Color,
    val onBottomBar: Color,
    val info: Color = tealLight,
    val onInfo: Color = whiteLight,
    val success: Color = greenLight,
    val onSuccess: Color = whiteLight,
    val warn: Color = yellowLight,
    val onWarn: Color = blackLight,
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
        primary = blueLight,
        primaryVariant = purpleLight,
        onPrimary = whiteLight,

        secondary = yellowLight,
        secondaryVariant = greenLight,
        onSecondary = whiteLight,

        background = whiteLight,
        onBackground = blackLight,

        surface = whiteLight,
        onSurface = blackLight,

        error = redLight,
        onError = whiteLight
    ),
    topBar = blueLight,
    onTopBar = whiteDark,

    bottomBar = blueLight,
    onBottomBar = whiteDark,

    statusBar = blueLight,

    card = card,
    cardAction = blackLighter,
    onCard = blackLight,
    cardBorder = card,

    speaker = card
)

internal val BlueThemeDark = ColorTheme(
    material = darkColors(
        primary = blueDark,
        primaryVariant = purpleDark,
        onPrimary = blackDark,

        secondary = yellowDark,
        secondaryVariant = greenDark,
        onSecondary = blackDark,

        background = blackDark,
        onBackground = whiteDark,

        surface = blackDark,
        onSurface = whiteDark,

        error = redDark,
        onError = blackDark
    ),
    topBar = blueDark,
    onTopBar = whiteDark,

    bottomBar = blueDark,
    onBottomBar = whiteDark,

    statusBar = blueDark,

    card = yellowDark,
    cardAction = blackDark,
    onCard = whiteDark,
    cardBorder = yellowDark,

    speaker = blueDark
)

internal fun getThemeColors(darkTheme: Boolean) = if (darkTheme) BlueThemeDark else BlueThemeLight

internal val LocalColors = staticCompositionLocalOf { BlueThemeLight }
