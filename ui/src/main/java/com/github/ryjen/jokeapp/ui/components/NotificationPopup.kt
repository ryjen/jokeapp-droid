package com.github.ryjen.jokeapp.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.ryjen.jokeapp.ui.theme.AppTheme

@Composable
fun Notifications.color(): Color =
    when (this) {
        is Notifications.Danger -> AppTheme.colors.error
        is Notifications.Info -> Color.Cyan
        is Notifications.Success -> Color.Green
        is Notifications.Warn -> Color.Yellow
    }

@Composable
fun NotificationPopup(
    notification: Notifications,
    defaultAction: () -> Unit
) {
    Snackbar(
        modifier = Modifier.padding(16.dp),
        content = {
            Text(text = notification.message)
        },
        backgroundColor = notification.color(),
        action = {
            notification.action?.let { (label, onAction) ->
                TextButton(onClick = onAction ?: defaultAction) {
                    Text(
                        text = label,
                        color = MaterialTheme.colors.primary
                    )
                }
            }
        }
    )
}
