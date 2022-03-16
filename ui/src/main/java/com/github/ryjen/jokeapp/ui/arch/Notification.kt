package com.github.ryjen.jokeapp.ui.arch

data class NotificationAction(
    val label: String,
    val onAction: (() -> Unit)? = null
)

interface NotificationType {
    val message: String
    val action: NotificationAction?
}

sealed class Notification : NotificationType {
    data class Danger(
        override val message: String,
        override val action: NotificationAction? = null,
    ) : Notification()

    data class Warn(
        override val message: String,
        override val action: NotificationAction? = null
    ) : Notification()

    data class Info(
        override val message: String,
        override val action: NotificationAction? = null
    ) : Notification()

    data class Success(
        override val message: String,
        override val action: NotificationAction? = null
    ) : Notification()
}
