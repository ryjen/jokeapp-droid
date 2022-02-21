package com.github.ryjen.jokeapp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.github.ryjen.jokeapp.ui.navigation.Router

@Composable
fun NotificationHost(
    host: SnackbarHostState,
    router: Router,
    modifier: Modifier = Modifier
) {
    val notificationState = router.notifications().collectAsState()

    val notification = notificationState.value ?: return

    SnackbarHost(
        hostState = host,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(Alignment.Bottom),
        snackbar = { data ->
            NotificationPopup(notification) {
                data.dismiss()
            }
        }
    )
}
