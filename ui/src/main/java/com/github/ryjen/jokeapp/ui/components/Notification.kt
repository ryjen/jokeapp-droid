package com.github.ryjen.jokeapp.ui.components

data class NotificationAction(
    val label: String,
    val onAction: (() -> Unit)? = null
)

interface Notification {
    val message: String
    val action: NotificationAction?
}

sealed class Notifications : Notification {
    data class Danger(
        override val message: String,
        override val action: NotificationAction? = null
    ) : Notifications()

    data class Warn(
        override val message: String,
        override val action: NotificationAction? = null
    ) : Notifications()

    data class Info(
        override val message: String,
        override val action: NotificationAction? = null
    ) : Notifications()

    data class Success(
        override val message: String,
        override val action: NotificationAction? = null
    ) : Notifications()
}
