package com.github.ryjen.jokeapp.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.ryjen.jokeapp.domain.arch.DateSerializer
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import java.util.*

@Entity(tableName = "jokes")
@Parcelize
data class Joke(
    @PrimaryKey val id: String,
    val content: String,
    val created: Date? = null,
    val isFavorite: Boolean = false
) : Parcelable
