package com.github.ryjen.jokeapp.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.ryjen.jokeapp.ui.theme.ThemeColors

@Composable
fun Notifications.background(): Color =
    when (this) {
        is Notifications.Danger -> ThemeColors.material.error
        is Notifications.Info -> ThemeColors.info
        is Notifications.Success -> ThemeColors.success
        is Notifications.Warn -> ThemeColors.warn
    }


@Composable
fun Notifications.foreground(): Color =
    when (this) {
        is Notifications.Danger -> ThemeColors.material.onError
        is Notifications.Info -> ThemeColors.onInfo
        is Notifications.Success -> ThemeColors.onSuccess
        is Notifications.Warn -> ThemeColors.onWarn
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
        backgroundColor = notification.background(),
        action = {
            notification.action?.let { (label, onAction) ->
                TextButton(onClick = onAction ?: defaultAction) {
                    Text(
                        text = label,
                        color = notification.foreground()
                    )
                }
            }
        }
    )
}
