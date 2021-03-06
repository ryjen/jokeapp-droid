package com.github.ryjen.jokeapp.ui.jokes.favourites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.ui.components.PopUpDialog
import com.github.ryjen.jokeapp.ui.components.Share
import com.github.ryjen.jokeapp.ui.theme.*

@Composable
fun FavoritesScreen(viewModel: FavoritesViewModel) {

    val state by viewModel.state().collectAsState()

    FavoritesContent(state, viewModel::dispatch)
}

@Composable
fun FavoritesContent(state: FavoritesViewState, onAction: (FavoritesAction) -> Unit) {
    val (showDeleteConfirm, setDeleteConfirm) = remember { mutableStateOf<Joke?>(null) }
    val context = LocalContext.current

    LazyColumn(modifier = Modifier.padding(top = ThemeDimensions.padding.medium)) {
        items(state.jokes) { joke ->
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Card(
                    elevation = ThemeDimensions.elevations.card,
                    backgroundColor = ThemeColors.card,
                    contentColor = ThemeColors.onCard,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(ThemeDimensions.padding.medium)
                        .defaultMinSize(minHeight = 50.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .padding(ThemeDimensions.padding.medium)
                            .padding(top = ThemeDimensions.padding.small),
                        text = joke.content,
                        style = ThemeTypography.material.body1
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(
                            start = 0.dp, top = ThemeDimensions.padding.small,
                            end = ThemeDimensions.padding.small, 0.dp
                        )
                ) {
                    IconButton(
                        modifier = Modifier
                            .size(ThemeDimensions.icons.small),
                        onClick = {
                            Share.text(context, joke.content)
                        }) {
                        Icon(
                            tint = ThemeColors.material.primary,
                            imageVector = ThemeImages.share,
                            modifier = Modifier
                                .size(ThemeDimensions.icons.small),
                            contentDescription = null
                        )
                    }

                    Spacer(modifier = Modifier.width(ThemeDimensions.padding.medium))

                    IconButton(
                        modifier = Modifier
                            .size(ThemeDimensions.icons.small),
                        onClick = {
                            setDeleteConfirm(joke)
                        }) {
                        Icon(
                            tint = ThemeColors.material.error,
                            imageVector = ThemeImages.delete,
                            modifier = Modifier
                                .size(ThemeDimensions.icons.small)
                                .background(ThemeColors.material.onError, CircleShape),
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
    showDeleteConfirm?.let { joke ->
        PopUpDialog(
            message = "Ok to remove?",
            title = "Remove Favorite",
            dismiss = { setDeleteConfirm(null) },
        ) {
            onAction(FavoritesAction.Remove(joke))
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = previewBackground
)
@Composable
fun FavoritesPreview() {
    FavoritesContent(state = FavoritesViewState(
        jokes = listOf(
            Joke(id = "123", content = "why did the chicken cross the road"),
            Joke(id = "234", content = "why did the orange stop in the middle of the road")
        )
    ), onAction = {})
}
