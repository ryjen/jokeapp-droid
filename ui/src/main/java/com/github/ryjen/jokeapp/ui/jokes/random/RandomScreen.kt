package com.github.ryjen.jokeapp.ui.jokes.random

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.ui.arch.Failure
import com.github.ryjen.jokeapp.ui.components.DotsPulsing
import com.github.ryjen.jokeapp.ui.theme.*
import com.smarttoolfactory.speechbubble.ArrowAlignment
import com.smarttoolfactory.speechbubble.Bubble
import com.smarttoolfactory.speechbubble.drawBubble
import com.smarttoolfactory.speechbubble.rememberBubbleState

@Composable
fun RandomJokeScreen(viewModel: RandomJokeViewModel) {

    val state by viewModel.state.collectAsState()

    RandomJokeContent(state, viewModel::onAction)
}

@Composable
fun RandomJokeContent(state: JokeState, onAction: (JokeActions) -> Unit = {}) {
    val scrollState = rememberScrollState()

    val bubbleState = rememberBubbleState(
        backgroundColor = ThemeColors.card,
        alignment = ArrowAlignment.BottomRight,
        drawArrow = true,
        arrowHeight = ThemeDimensions.bubbleArrow,
        arrowOffsetX = -ThemeDimensions.padding.large,
        cornerRadius = ThemeDimensions.padding.medium,
        shadow = Bubble.shadow(
            color = ThemeColors.shadow,
            elevation = ThemeDimensions.elevations.card
        ),
        padding = Bubble.padding(ThemeDimensions.padding.small)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
            .padding(ThemeDimensions.padding.large)
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(ThemeDimensions.padding.small),
        ) {
            Box(
                modifier = Modifier
                    .drawBubble(bubbleState)
                    .padding(ThemeDimensions.padding.medium)
            ) {
                state.error?.let {
                    Text(
                        text = it.message(),
                        modifier = Modifier.padding(ThemeDimensions.padding.medium),
                        style = ThemeTypography.bubbleSmall,
                        fontWeight = FontWeight.Bold,
                        color = ThemeColors.material.error
                    )
                } ?: run {
                    state.joke?.let {
                        Text(
                            text = it.content,
                            color = ThemeColors.onCard,
                            style = ThemeTypography.bubbleLarge,
                        )
                    } ?: DotsPulsing(modifier = Modifier.padding(ThemeDimensions.padding.large))
                }
            }
            Icon(
                imageVector = ThemeImages.speaker,
                tint = ThemeColors.speaker,
                modifier = Modifier
                    .padding(top = ThemeDimensions.padding.large)
                    .size(ThemeDimensions.icons.large)
                    .background(ThemeColors.onCard, CircleShape)
                    .border(1.dp, ThemeColors.onCard, CircleShape)
                    .align(Alignment.End),
                contentDescription = null
            )
        }
    }
}

@Composable
@Preview(
    showBackground = true,
    backgroundColor = previewBackground
)
fun RandomJokeScreenPreview() {
    RandomJokeContent(
        JokeState(
            joke = Joke(
                id = "1234",
                content = "why did the chicken cross the road?"
            )
        )
    )
}


@Composable
@Preview(
    showBackground = true,
    backgroundColor = previewBackground
)
fun RandomJokeErrorPreview() {
    RandomJokeContent(
        JokeState(
            error = Failure.Message("Could not cross the road")
        )
    )
}

@Composable
@Preview(
    showBackground = true,
    backgroundColor = previewBackground
)
fun RandomJokeLoadingPreview() {
    RandomJokeContent(
        JokeState()
    )
}
