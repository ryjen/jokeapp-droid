package com.github.ryjen.jokeapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.ryjen.jokeapp.ui.arch.Notification
import com.github.ryjen.jokeapp.ui.arch.NotificationAction
import com.github.ryjen.jokeapp.ui.theme.ThemeColors

@Composable
fun Notification.background(): Color =
    when (this) {
        is Notification.Danger -> ThemeColors.material.error
        is Notification.Info -> ThemeColors.info
        is Notification.Success -> ThemeColors.success
        is Notification.Warn -> ThemeColors.warn
    }


@Composable
fun Notification.foreground(): Color =
    when (this) {
        is Notification.Danger -> ThemeColors.material.onError
        is Notification.Info -> ThemeColors.onInfo
        is Notification.Success -> ThemeColors.onSuccess
        is Notification.Warn -> ThemeColors.onWarn
    }

@Composable
fun NotificationPopup(
    notification: Notification,
    defaultAction: () -> Unit
) {
    Snackbar(
        modifier = Modifier.padding(16.dp),
        content = {
            Text(
                text = notification.message,
                color = notification.foreground()
            )
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

@Preview
@Composable
fun NotificationPopupPreview() {

    Column {

        NotificationPopup(
            notification = Notification.Danger(
                message = "this is a test",
                action = NotificationAction("Undo") {}
            )
        ) {
        }


        NotificationPopup(
            notification = Notification.Warn(
                message = "this is a test",
                action = NotificationAction("Undo") {}
            )
        ) {
        }

        NotificationPopup(
            notification = Notification.Info(
                message = "this is a test",
                action = NotificationAction("Undo") {}
            )
        ) {
        }

        NotificationPopup(
            notification = Notification.Success(
                message = "this is a test",
                action = NotificationAction("Undo") {}
            )
        ) {
        }
    }
}
