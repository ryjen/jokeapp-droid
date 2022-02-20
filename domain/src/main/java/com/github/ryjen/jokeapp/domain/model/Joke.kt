package com.github.ryjen.jokeapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Joke(
    val id: String,
    val content: String,
    val created: Date? = null,
    val isFavorite: Boolean = false
) : Parcelable
