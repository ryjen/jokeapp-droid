package com.github.ryjen.jokeapp.ui.jokes.random

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.ryjen.jokeapp.domain.model.Failure
import com.github.ryjen.jokeapp.domain.model.Joke
import org.koin.androidx.compose.inject

@Composable
fun JokeScreen() {

    val viewModel: JokeViewModel by inject()

    val state = viewModel.state.collectAsState()

    state.value.error?.let {
        ErrorContent(it)
    } ?: run {
        state.value.joke?.let {
            JokeContent(it)
        } ?: run {
            LoadingContent()
        }
    }
}

@Composable
fun JokeContent(joke: Joke) {
    val scrollState = rememberScrollState()

    Text(
        text = joke.content,
        modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)
        .padding(16.dp))
}

@Composable
fun LoadingContent() {
    Text(text = "Loading")
}

@Composable
fun ErrorContent(err: Failure) {
    Text(text = err.toString())
}
