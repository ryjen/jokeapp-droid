package com.github.ryjen.jokeapp.ui.jokes.favourites

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.ui.R
import com.github.ryjen.jokeapp.ui.components.TabBar
import com.github.ryjen.jokeapp.ui.navigation.Router
import com.github.ryjen.jokeapp.ui.navigation.rememberRouter
import com.github.ryjen.jokeapp.ui.theme.LocalDimensions

@Composable
fun FavoritesFooter(router: Router, viewModel: FavoritesViewModel) {
    val state by viewModel.state.collectAsState()

    FavoritesFooterContent(router, state)
}

@Composable
fun FavoritesFooterContent(router: Router, state: FavoritesState) {
    val res = LocalContext.current.resources

    Column {
        Text(
            modifier = Modifier
                .padding(LocalDimensions.current.padding.medium)
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            textAlign = TextAlign.Center,
            text = res.getQuantityString(
                R.plurals.joke_count, 0, state.jokes.size
            )
        )

        TabBar(router)
    }
}

@ExperimentalAnimationApi
@Composable
@Preview
fun FavoritesFooterPreview() {
    FavoritesFooterContent(
        router = rememberRouter(),
        state = FavoritesState(
            jokes = listOf(
                Joke("123", "arst"),
                Joke("123", "arst")
            )
        )
    )
}
