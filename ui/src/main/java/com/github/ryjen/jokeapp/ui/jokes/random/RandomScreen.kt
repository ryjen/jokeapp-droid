package com.github.ryjen.jokeapp.ui.jokes.random

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.ui.arch.Failure
import com.github.ryjen.jokeapp.ui.components.DotsPulsing
import com.github.ryjen.jokeapp.ui.theme.*
import com.smarttoolfactory.speechbubble.*

@Composable
fun RandomJokeScreen(viewModel: RandomJokeViewModel) {

    val state by viewModel.state.collectAsState()

    state.error?.let {
        RandomJokeErrorContent(it)
    } ?: run {
        state.joke?.let {
            RandomJokeContent(it)
        } ?: run {
            LoadingContent()
        }
    }
}

@Composable
fun RandomJokeContent(joke: Joke) {
    val scrollState = rememberScrollState()
    val bubbleState = rememberBubbleState(
        backgroundColor = ThemeColors.card,
        alignment = ArrowAlignment.BottomRight,
        drawArrow = true,
        arrowHeight = ThemeDimensions.bubbleArrow,
        arrowOffsetX = -ThemeDimensions.padding.large,
        cornerRadius = ThemeDimensions.padding.medium,
        shadow = BubbleShadow(
            elevation = ThemeDimensions.elevations.card
        ),
        padding = Padding(8.dp)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
            .padding(ThemeDimensions.padding.large)
    ) {
        Column(
            modifier = Modifier.verticalScroll(scrollState)
                .padding(ThemeDimensions.padding.small),
            ) {
            Text(
                text = joke.content,
                color = ThemeColors.onCard,
                style = ThemeTypography.material.h4,
                textAlign = TextAlign.Center,
                modifier = Modifier.drawBubble(bubbleState)
                    .padding(ThemeDimensions.padding.medium)
            )
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
fun LoadingContent() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(ThemeDimensions.padding.large)
            .wrapContentSize(Alignment.Center)
    ) {
        DotsPulsing()
    }
}

@Composable
fun RandomJokeErrorContent(err: Failure) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(ThemeDimensions.padding.large)
            .wrapContentSize(Alignment.Center)
    ) {
        Box(
            modifier = Modifier
                .padding(ThemeDimensions.padding.medium)
                .background(
                    color = ThemeColors.material.error,
                    shape = ThemeShapes.bubble
                )
        ) {
            Text(
                text = err.message(),
                style = ThemeTypography.material.body1,
                color = ThemeColors.material.onError
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
        Joke(
            id = "1234",
            content = "why did the chicken cross the road?"
        )
    )
}

@Composable
@Preview(
    showBackground = true,
    backgroundColor = previewBackground
)
fun LoadingPreview() {
    LoadingContent()
}

@Preview(
    showBackground = true,
    backgroundColor = previewBackground
)
@Composable
fun ErrorScreenPreview() {
    MainTheme {
        RandomJokeErrorContent(err = Failure.Message("Error Testing"))
    }
}
