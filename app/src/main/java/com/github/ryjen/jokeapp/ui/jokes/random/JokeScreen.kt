package com.github.ryjen.jokeapp.ui.jokes.random

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.ryjen.jokeapp.domain.State
import com.github.ryjen.jokeapp.domain.model.Joke

@Composable
fun JokeScreen (
    viewModel: JokeViewModel = viewModel(),
) {

    val state = viewModel.state.collectAsState()

    when(val s = state.value) {
        is State.Loading -> LoadingContent()
        is State.Failure -> ErrorContent(s.error)
        is State.Success -> JokeContent(s.data)
    }
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
fun ErrorContent(err: Throwable) {
    Text(text = err.toString())
}
