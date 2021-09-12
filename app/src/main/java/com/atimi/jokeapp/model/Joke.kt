package com.atimi.jokeapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.io.Serializable
import java.util.*

@Entity(tableName = "jokes")
@Parcelize
data class Joke(
    @PrimaryKey val id: String,
    val joke: String,
    val status: Int,
    val created: Date? = null
) : Parcelable, Serializable
