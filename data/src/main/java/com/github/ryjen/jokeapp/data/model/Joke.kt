package com.github.ryjen.jokeapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
import com.github.ryjen.jokeapp.domain.model.Joke as DomainJoke

@Entity(tableName = "jokes")
data class Joke(
    @PrimaryKey val id: String,
    val content: String,
    val created: Date? = null,
    val isFavorite: Boolean = false
)

fun Joke.toDomain() =
    DomainJoke(id = id, content = content, created = created, isFavorite = isFavorite)

fun DomainJoke.toData() =
    Joke(id = id, content = content, created = created, isFavorite = isFavorite)

