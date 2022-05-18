package com.github.ryjen.jokeapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class JokeResponse(
    val id: String,
    val joke: String,
    val status: Int
)
