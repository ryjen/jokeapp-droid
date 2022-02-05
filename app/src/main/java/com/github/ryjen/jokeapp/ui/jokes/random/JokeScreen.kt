package com.github.ryjen.jokeapp.ui.jokes.random

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.ryjen.jokeapp.domain.model.Failure
import com.github.ryjen.jokeapp.domain.model.Joke

@Composable
fun JokeScreen(
    viewModel: JokeViewModel = viewModel(),
) {

    val state = viewModel.state.collectAsState()

    state.value.error?.let {
        ErrorContent(it)
    } ?: state.value.joke?.let {
        JokeContent(it)
    } ?: LoadingContent()
}

@Composable
fun JokeContent(joke: Joke) {
    Text(text = joke.content)
}

@Composable
fun LoadingContent() {
    Text(text = "Loading")
}

@Composable
fun ErrorContent(err: Failure) {
    Text(text = err.toString())
}
