package com.github.ryjen.jokeapp.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.github.ryjen.jokeapp.R

data class Tab(@StringRes val title: Int,
               @DrawableRes val icon: Int)

val Tabs = mapOf(
    Pair( Routes.RANDOM_JOKE, Tab(R.string.action_refresh, R.drawable.sync)),
    Pair( Routes.FAVORITE_JOKES, Tab(R.string.action_favorites, R.drawable.bookmarks)),
)
