package com.github.ryjen.jokeapp.ui.jokes.random

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.ui.arch.Failure
import com.github.ryjen.jokeapp.ui.theme.AppTheme
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import org.koin.androidx.compose.inject

@Composable
fun JokeScreen() {

    val viewModel: RandomViewModel by inject()

    val state = viewModel.state.collectAsState()

    state.value.error?.let {
        ErrorContent(it)
    } ?: run {
        JokeContent(state.value.joke)
    }
}

@Composable
fun JokeContent(joke: Joke?) {
    val scrollState = rememberScrollState()

    Text(
        text = joke?.content ?: "",
        style = AppTheme.typography.h4,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxSize()
            .placeholder(
                visible = joke == null,
                highlight = PlaceholderHighlight.shimmer(Color.Gray),
                color = Color.Transparent
            )
            .verticalScroll(scrollState)
            .wrapContentSize(Alignment.Center)
            .padding(vertical = 16.dp, horizontal = 80.dp)
    )
}

@Composable
fun ErrorContent(err: Failure) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .wrapContentSize(Alignment.Center)
    ) {
        Box(
            modifier = Modifier
                .padding(16.dp)
                .background(
                    color = Color.Red,
                    shape = RoundedCornerShape(16.dp)
                )
        ) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = err.message(),
                style = AppTheme.typography.body1,
                color = Color.White
            )
        }
    }
}

@Composable
@Preview
fun RandomScreenPreview() {
    JokeContent(
        Joke(
            id = "1234",
            content = "why did the chicken cross the road?"
        )
    )
}

@Preview
@Composable
fun ErrorScreenPreview() {
    ErrorContent(err = Failure.Message("Error Testing"))
}
