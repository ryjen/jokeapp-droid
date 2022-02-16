package com.github.ryjen.jokeapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class JokeResponse(
    val id: String,
    val joke: String,
    val status: Int
): Parcelable {
    fun asJoke(): Joke = Joke(
        id = id,
        content = joke
    )
}
