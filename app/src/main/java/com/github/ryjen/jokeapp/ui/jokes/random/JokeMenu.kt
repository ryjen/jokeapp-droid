package com.github.ryjen.jokeapp.ui.jokes.random

import android.content.Context
import android.content.Intent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.ryjen.jokeapp.R

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
fun JokeMenu(viewModel: JokeViewModel = viewModel()) {
    var showMenu by remember { mutableStateOf(false) }
    val context = LocalContext.current

    TopAppBar(
        title = { Text("Random Joke") },
        actions = {
            viewModel.currentJoke?.let { joke ->
                IconButton(onClick = {
                    shareText(context, joke.content)
                }) {
                    Icon(painterResource(id = R.drawable.share), null)
                    Text(text = stringResource(R.string.action_share))
                }

                if (joke.isFavorite) {
                    IconButton(onClick = {
                        viewModel.addJokeToFavorites(joke)
                    }) {
                        Icon(painterResource(id = R.drawable.bookmark_remove), null)
                        Text(text = stringResource(R.string.action_remove))
                    }
                } else {
                    IconButton(onClick = {
                        viewModel.removeJokeFromFavorites(joke)
                    }) {
                        Icon(painterResource(id = R.drawable.bookmark_add), null)
                        Text(text = stringResource(R.string.action_add))
                    }
                }
            }

            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(Icons.Default.MoreVert, null)
            }
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false }
            ) {
            }
        }
    )
}
