package com.github.ryjen.jokeapp.ui.jokes.favourites

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.ryjen.jokeapp.ui.R

@Composable
fun FavoritesMenu() {
    TopAppBar(
        title = {
            Text(text = stringResource(R.string.app_name))
        },
        elevation = 0.dp,
    )
}
