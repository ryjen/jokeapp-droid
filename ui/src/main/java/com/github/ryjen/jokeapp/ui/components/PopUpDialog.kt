package com.github.ryjen.jokeapp.ui.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
fun PopUpDialog(
    title: String,
    message: String,
    dismiss: () -> Unit,
    onAction: () -> Unit = {}
) {

    AlertDialog(
        onDismissRequest = {
        },
        title = {
            Text(text = title)
        },
        confirmButton = {
            Button(
                onClick = {
                    onAction()
                    dismiss()
                },
            ) {
                Text(stringResource(id = android.R.string.ok))
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    dismiss()
                },
            ) {
                Text(stringResource(android.R.string.cancel))
            }
        },
        text = {
            Text(message)
        },
    )
}
