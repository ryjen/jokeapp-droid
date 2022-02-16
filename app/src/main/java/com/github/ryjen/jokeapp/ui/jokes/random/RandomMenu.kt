package com.github.ryjen.jokeapp.ui.jokes.random

import android.content.Context
import android.content.Intent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.github.ryjen.jokeapp.R
import org.koin.androidx.compose.inject

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
fun JokeMenu() {
    val viewModel: RandomViewModel by inject()
    val state = viewModel.state.collectAsState()
    var showMenu by remember { mutableStateOf(false) }
    val context = LocalContext.current

    TopAppBar(
        title = { Text("Random Joke") },
        elevation = 0.dp,
        actions = {
            state.value.joke?.let { joke ->
                IconButton(onClick = {
                    shareText(context, joke.content)
                }) {
                    Icon(painterResource(id = R.drawable.share), null)
                }

                if (joke.isFavorite) {
                    IconButton(onClick = {
                        viewModel.removeJokeFromFavorites(joke)
                    }) {
                        Icon(painterResource(id = R.drawable.bookmark_remove), null)
                    }
                } else {
                    IconButton(onClick = {
                        viewModel.addJokeToFavorites(joke)
                    }) {
                        Icon(painterResource(id = R.drawable.bookmark_add), null)
                    }
                }
            }

            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(Icons.Default.MoreVert, null)
            }
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = {
                    showMenu = false
                }
            ) {
            }
        }
    )
}
