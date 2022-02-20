package com.github.ryjen.jokeapp.ui.theme

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.vector.ImageVector
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.regular.Bookmark
import compose.icons.fontawesomeicons.solid.Bookmark
import compose.icons.fontawesomeicons.solid.ShareAlt
import compose.icons.fontawesomeicons.solid.Sync


/**
 * Images that can vary by theme.
 */
@Immutable
data class Images(
    val bookmarks: ImageVector,
    val bookmarkAdd: ImageVector,
    val bookmarkRemove: ImageVector,
    val refresh: ImageVector,
    val more: ImageVector,
    val share: ImageVector
)

val LightImages = Images(
    bookmarkAdd = FontAwesomeIcons.Regular.Bookmark,
    bookmarkRemove = FontAwesomeIcons.Solid.Bookmark,
    bookmarks = FontAwesomeIcons.Solid.Bookmark,
    refresh = FontAwesomeIcons.Solid.Sync,
    more = Icons.Default.MoreVert,
    share = FontAwesomeIcons.Solid.ShareAlt
)

val DarkImages = LightImages

internal val LocalImages = staticCompositionLocalOf {
    LightImages
}
