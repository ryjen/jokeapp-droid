package com.github.ryjen.jokeapp.ui.theme

import com.github.ryjen.jokeapp.R
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf

/**
 * Images that can vary by theme.
 */
@Immutable
data class Images(
    @DrawableRes val bookmarks: Int,
    @DrawableRes val bookmarkAdd: Int,
    @DrawableRes val bookmarkRemove: Int,
)

val LightImages = Images(
    bookmarkAdd = R.drawable.bookmark_add,
    bookmarkRemove = R.drawable.bookmark_remove,
    bookmarks = R.drawable.bookmarks,
)

val DarkImages = Images(
    bookmarkAdd = R.drawable.bookmark_add,
    bookmarkRemove = R.drawable.bookmark_remove,
    bookmarks = R.drawable.bookmarks,
)

internal val LocalImages = staticCompositionLocalOf {
    LightImages
}
