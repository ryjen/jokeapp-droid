package com.github.ryjen.jokeapp.ui.jokes.random

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.github.ryjen.jokeapp.ui.components.TabBar
import com.github.ryjen.jokeapp.ui.navigation.Router
import com.github.ryjen.jokeapp.ui.navigation.rememberRouter

@Composable
fun RandomJokeFooter(router: Router) {
    RandomJokeFooterContent(router)
}

@Composable
fun RandomJokeFooterContent(router: Router) {
    TabBar(router)
}

@ExperimentalAnimationApi
@Composable
@Preview
fun RandomJokeFooterPreview() {
    RandomJokeFooterContent(router = rememberRouter())
}
