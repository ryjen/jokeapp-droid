package com.github.ryjen.jokeapp.ui.jokes.random

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.ui.R
import com.github.ryjen.jokeapp.ui.components.Share
import com.github.ryjen.jokeapp.ui.theme.ThemeColors
import com.github.ryjen.jokeapp.ui.theme.ThemeDimensions
import com.github.ryjen.jokeapp.ui.theme.ThemeImages

@Composable
fun RandomJokeMenu(viewModel: RandomJokeViewModel) {
    val state by viewModel.state.collectAsState()

    RandomJokeMenu(state, viewModel::dispatch)
}

@Composable
fun RandomJokeMenu(state: RandomJokeState, onAction: (RandomJokeAction) -> Unit) {
    val context = LocalContext.current

    TopAppBar(
        title = { Text(stringResource(id = R.string.app_name)) },
        backgroundColor = ThemeColors.topBar,
        contentColor = ThemeColors.onTopBar,
        elevation = 0.dp,
        actions = {
            IconButton(onClick = {
                onAction(RandomJokeAction.RefreshClick)
            }) {
                Icon(
                    imageVector = ThemeImages.refresh,
                    modifier = Modifier.size(ThemeDimensions.icons.small),
                    contentDescription = null
                )
            }

            state.joke?.let { joke ->

                if (joke.isFavorite) {
                    IconButton(
                        modifier = Modifier.testTag("remove"),
                        onClick = {
                            onAction(RandomJokeAction.UnFavorite(joke))
                        }) {
                        Icon(
                            imageVector = ThemeImages.bookmarkRemove,
                            modifier = Modifier.size(ThemeDimensions.icons.small),
                            contentDescription = null
                        )
                    }
                } else {
                    IconButton(
                        modifier = Modifier.testTag("add"),
                        onClick = {
                            onAction(RandomJokeAction.Favorite(joke))
                        }) {
                        Icon(
                            imageVector = ThemeImages.bookmarkAdd,
                            modifier = Modifier.size(ThemeDimensions.icons.small),
                            contentDescription = null
                        )
                    }
                }
                IconButton(
                    modifier = Modifier.testTag("share"),
                    onClick = {
                        Share.text(context, joke.content)
                    }) {
                    Icon(
                        imageVector = ThemeImages.share,
                        modifier = Modifier.size(ThemeDimensions.icons.small),
                        contentDescription = null
                    )
                }

            }
        }
    )
}

@Preview
@Composable
fun RandomMenuPreview() {
    RandomJokeMenu(
        RandomJokeState(
            joke = Joke(
                id = "123",
                content = "this is a test"
            )
        )
    ) {}
}
