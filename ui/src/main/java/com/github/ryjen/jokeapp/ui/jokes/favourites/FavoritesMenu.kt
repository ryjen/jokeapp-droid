package com.github.ryjen.jokeapp.ui.jokes.favourites

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.ryjen.jokeapp.ui.R

@Composable
fun FavoritesMenu(viewModel: FavoritesViewModel) {
    TopAppBar(
        title = {
            Text(text = stringResource(R.string.action_favorites))
        },
        elevation = 0.dp,
    )
}
