package com.smarttoolfactory.speechbubble

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


internal fun Modifier.materialShadow(
    bubbleState: BubbleState,
    path: Path,
    translateIfNeeded: Boolean = false
) = composed(
    inspectorInfo = {
        name = "shadow"
        value = bubbleState.shadow
    },
    factory = {
        bubbleState.shadow?.let {

            val paint: Paint = remember(bubbleState) {
                Paint()
            }

            val frameworkPaint: NativePaint = remember(bubbleState) {
                paint.asFrameworkPaint()
            }

            drawBehind {
                if (translateIfNeeded) {
                    val left = if (bubbleState.isHorizontalLeftAligned())
                        -bubbleState.arrowWidth.toPx() else 0f
                    translate(left = left) {
                        drawShadow(bubbleState, frameworkPaint, path, paint)
                    }
                } else {
                    drawShadow(bubbleState, frameworkPaint, path, paint)
                }
            }
        } ?: this
    }
)

fun DrawScope.drawShadow(
    bubbleState: BubbleState,
    frameworkPaint: NativePaint,
    path: Path,
    paint: Paint
) {
    bubbleState.shadow?.let { shadow ->

        if (shadow.useSoftwareLayer) {
            this.drawIntoCanvas {

                val color = shadow.color

                val dx = shadow.offsetX.toPx() * 0.2f
                val dy = shadow.offsetY.toPx() * 0.7f
                val radius = shadow.shadowRadius.toPx()

                val shadowColor = color
                    .copy(alpha = shadow.alpha)
                    .toArgb()
                val transparent = color
                    .copy(alpha = 0f)
                    .toArgb()

                frameworkPaint.color = transparent

                frameworkPaint.setShadowLayer(
                    radius,
                    -dx,
                    dy,
                    shadowColor
                )
                it.drawPath(path, paint)
            }

        } else {
            val dx = shadow.offsetX.toPx() * 0.2f
            val dy = shadow.offsetY.toPx() * 0.7f
            translate(-dx, dy) {
                drawPath(color = shadow.color.copy(shadow.alpha), path = path)
            }
        }
    }
}

@Stable
@Immutable
data class BubbleShadow internal constructor(
    val color: Color,
    val alpha: Float = .7f,
    val shadowRadius: Dp = 1.dp,
    val offsetY: Dp = 1.dp,
    val offsetX: Dp = 1.dp,
    val useSoftwareLayer: Boolean = true
)
