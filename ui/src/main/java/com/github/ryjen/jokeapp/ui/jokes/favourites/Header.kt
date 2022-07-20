package com.github.ryjen.jokeapp.ui.jokes.favourites

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.ryjen.jokeapp.ui.R

@Composable
fun FavoritesHeader() {
    FavoritesHeaderContent()
}

@Composable
fun FavoritesHeaderContent() {
    TopAppBar(
        title = {
            Text(text = stringResource(R.string.app_name))
            Text(text = " - ")
            Text(text = stringResource(R.string.action_favorites))
        },
        elevation = 0.dp,
    )
}

@Preview
@Composable
fun FavoritesMenuPreview() {
    FavoritesHeaderContent()
}
