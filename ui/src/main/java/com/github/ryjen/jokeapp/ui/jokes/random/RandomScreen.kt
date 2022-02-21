package com.github.ryjen.jokeapp.ui.jokes.random

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.ui.arch.Failure
import com.github.ryjen.jokeapp.ui.theme.*
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer

@Composable
fun RandomJokeScreen(viewModel: RandomJokeViewModel) {

    val state by viewModel.state.collectAsState()

    state.error?.let {
        RandomJokeErrorContent(it)
    } ?: run {
        RandomJokeContent(state.joke)
    }
}

@Composable
fun RandomJokeContent(joke: Joke?) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
            .padding(ThemeDimensions.padding.large)
    ) {
        Card(
            backgroundColor = ThemeColors.card,
            contentColor = ThemeColors.onCard,
            shape = ThemeShapes.bubble,
            modifier = Modifier
                .padding(ThemeDimensions.padding.small),
            elevation = ThemeDimensions.elevations.card
        ) {
            Text(
                text = joke?.content ?: "",
                color = ThemeColors.onCard,
                style = ThemeTypography.material.h4,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(ThemeDimensions.padding.medium)
                    .placeholder(
                        visible = joke == null,
                        highlight = PlaceholderHighlight.shimmer(ThemeColors.material.secondary),
                        color = Color.Transparent
                    )
            )
        }
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
