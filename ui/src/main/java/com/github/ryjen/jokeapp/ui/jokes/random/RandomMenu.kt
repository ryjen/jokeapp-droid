package com.github.ryjen.jokeapp.ui.jokes.random

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.github.ryjen.jokeapp.ui.R
import com.github.ryjen.jokeapp.ui.theme.AppTheme

fun shareText(context: Context, content: String) {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, content)
        type = "text/plain"
    }

    val shareIntent = Intent.createChooser(sendIntent, context.getString(R.string.action_share))
    context.startActivity(shareIntent)
}

@Composable
fun RandomJokeMenu(viewModel: RandomJokeViewModel) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    TopAppBar(
        title = { Text("Random Joke") },
        elevation = 0.dp,
        actions = {
            IconButton(onClick = {
                viewModel.refreshJoke()
            }) {
                Icon(
                    imageVector = AppTheme.images.refresh,
                    modifier = Modifier.size(AppTheme.dimens.navIcon),
                    contentDescription = null
                )
            }

            state.joke?.let { joke ->

                if (joke.isFavorite) {
                    IconButton(
                        modifier = Modifier.testTag("remove"),
                        onClick = {
                            viewModel.removeJokeFromFavorites(joke)
                        }) {
                        Icon(
                            imageVector = AppTheme.images.bookmarkRemove,
                            modifier = Modifier.size(AppTheme.dimens.navIcon),
                            contentDescription = null
                        )
                    }
                } else {
                    IconButton(
                        modifier = Modifier.testTag("add"),
                        onClick = {
                            viewModel.addJokeToFavorites(joke)
                        }) {
                        Icon(
                            imageVector = AppTheme.images.bookmarkAdd,
                            modifier = Modifier.size(AppTheme.dimens.navIcon),
                            contentDescription = null
                        )
                    }
                }
                IconButton(
                    modifier = Modifier.testTag("share"),
                    onClick = {
                        shareText(context, joke.content)
                    }) {
                    Icon(
                        imageVector = AppTheme.images.share,
                        modifier = Modifier.size(AppTheme.dimens.navIcon),
                        contentDescription = null
                    )
                }

            }
        }
    )
}
