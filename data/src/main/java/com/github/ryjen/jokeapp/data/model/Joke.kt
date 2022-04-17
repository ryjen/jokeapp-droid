package com.github.ryjen.jokeapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "jokes")
data class Joke(
    @PrimaryKey val id: String,
    val content: String,
    val created: Date? = null,
    val isFavorite: Boolean = false
)
