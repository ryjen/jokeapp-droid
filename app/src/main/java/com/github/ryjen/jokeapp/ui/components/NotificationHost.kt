package com.github.ryjen.jokeapp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NotificationHost(
    host: SnackbarHostState,
    modifier: Modifier = Modifier
) {
    SnackbarHost(
        hostState = host,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(Alignment.Bottom),
        snackbar = { data ->
            Notification(data.message, data.actionLabel) {
                data.dismiss()
            }
        }
    )
}

@Composable
fun Notification(message: String, actionLabel: String?, onAction: () -> Unit) {
    Snackbar(
        modifier = Modifier.padding(16.dp),
        content = {
            Text(text = message)
        },
        action = {
            actionLabel?.let { label ->
                TextButton(onClick = onAction) {
                    Text(
                        text = label,
                        color = MaterialTheme.colors.primary
                    )
                }
            }
        }
    )
}
