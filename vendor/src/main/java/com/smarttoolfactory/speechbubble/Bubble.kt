package com.smarttoolfactory.speechbubble

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object Bubble {

    fun corners(all: Dp = 0.dp) = BubbleCornerRadius(all, all, all, all)

    fun corners(
        topLeft: Dp = 0.dp,
        topRight: Dp = 0.dp,
        bottomLeft: Dp = 0.dp,
        bottomRight: Dp = 0.dp
    ) =
        BubbleCornerRadius(
            topLeft = topLeft,
            topRight = topRight,
            bottomLeft = bottomLeft,
            bottomRight = bottomRight
        )


    fun padding(
        start: Dp = 0.dp,
        top: Dp = 0.dp,
        end: Dp = 0.dp,
        bottom: Dp = 0.dp
    ) =
        BubblePadding(
            start = start,
            top = top,
            end = end,
            bottom = bottom
        )

    fun padding(
        all: Dp = 0.dp
    ) =
        BubblePadding(all, all, all, all)

    fun padding(
        horizontal: Dp = 0.dp,
        vertical: Dp = 0.dp
    ) =
        BubblePadding(horizontal, vertical, horizontal, vertical)


    /**
     * Creates a shadow instance.
     *
     * @param color Color of the shadow
     * @param alpha of the color of the shadow
     * @param useSoftwareLayer use software layer to draw shadow with blur
     * @param dX x offset of shadow blur
     * @param dY y offset of shadow blur
     * @param shadowRadius radius of shadow blur if useSoftwareLayer is set to true
     */
    fun shadow(
        color: Color,
        alpha: Float = .7f,
        useSoftwareLayer: Boolean = true,
        dX: Dp = 1.dp,
        dY: Dp = 1.dp,
        shadowRadius: Dp = 1.dp,
    ) =
        BubbleShadow(
            color,
            alpha,
            dX,
            dY,
            shadowRadius,
            useSoftwareLayer
        )

    /**
     * Creates a shadow instance.
     *
     * @param color Color of the shadow
     * @param alpha of the color of the shadow
     * @param useSoftwareLayer use software layer to draw shadow with blur
     * @param elevation elevation of the badge with shadow. Sets dx, dy,
     * and shadowRadius if software layer is used
     */
    fun shadow(
        color: Color,
        alpha: Float = .7f,
        useSoftwareLayer: Boolean = true,
        elevation: Dp = 1.dp
    ) =
        BubbleShadow(
            color,
            alpha,
            elevation,
            elevation,
            elevation,
            useSoftwareLayer
        )
}
