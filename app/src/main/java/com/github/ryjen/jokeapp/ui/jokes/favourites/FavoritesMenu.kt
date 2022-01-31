package com.github.ryjen.jokeapp.ui.jokes.favourites

import com.github.ryjen.jokeapp.R
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
fun FavoritesMenu() {
    TopAppBar(
        title = {
            Text(text = stringResource(R.string.action_favorites))
        }
    )
}
