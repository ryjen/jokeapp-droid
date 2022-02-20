package com.github.ryjen.jokeapp.ui.jokes.favourites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.ui.theme.AppTheme
import org.koin.androidx.compose.inject

@Composable
fun FavoritesScreen() {

    val viewModel: FavoritesViewModel by inject()

    val state = viewModel.state.collectAsState()

    FavoritesContent(state.value, viewModel::onAction)
}

@Composable
fun FavoritesContent(state: FavoritesState, onAction: (FavoritesActions) -> Unit) {

    LazyColumn {
        items(state.jokes) { joke ->
            Row(
                modifier = Modifier
                    .wrapContentSize(Alignment.Center)
            ) {
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = 50.dp)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        )
                ) {
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = joke.content,
                        style = AppTheme.typography.body1
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun FavoritesPreview() {
    FavoritesContent(state = FavoritesState(
        jokes = listOf(
            Joke(id = "123", content = "why did the chicken cross the road"),
            Joke(id = "234", content = "why did the orange stop in the middle of the road")
        )
    ), onAction = {})
}
