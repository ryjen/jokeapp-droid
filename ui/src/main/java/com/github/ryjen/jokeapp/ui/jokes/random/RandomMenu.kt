package com.github.ryjen.jokeapp.ui.jokes.random

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.ryjen.jokeapp.ui.R
import com.github.ryjen.jokeapp.ui.theme.AppTheme
import com.github.ryjen.jokeapp.ui.theme.Images
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Share

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
    var showMenu by remember { mutableStateOf(false) }
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
                    IconButton(onClick = {
                        viewModel.removeJokeFromFavorites(joke)
                    }) {
                        Icon(
                            imageVector = AppTheme.images.bookmarkRemove,
                            modifier = Modifier.size(AppTheme.dimens.navIcon),
                            contentDescription = null
                        )
                    }
                } else {
                    IconButton(onClick = {
                        viewModel.addJokeToFavorites(joke)
                    }) {
                        Icon(
                            imageVector = AppTheme.images.bookmarkAdd,
                            modifier = Modifier.size(AppTheme.dimens.navIcon),
                            contentDescription = null
                        )
                    }
                }
                IconButton(onClick = {
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
