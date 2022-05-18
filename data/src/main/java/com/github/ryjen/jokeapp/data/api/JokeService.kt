package com.github.ryjen.jokeapp.data.api

import com.github.ryjen.jokeapp.data.model.JokeResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import timber.log.Timber

private const val BASE_URL = "http://icanhazdadjoke.com"

class JokeService(
    private val client: HttpClient
) {
    suspend fun getRandomJoke(): JokeResponse? = try {
        client.get(BASE_URL).body()
    } catch (ex: Throwable) {
        Timber.d(ex)
        null
    }

    suspend fun getJoke(id: String): JokeResponse? = try {
        client.get("$BASE_URL/j/$id").body()
    } catch (ex: Throwable) {
        Timber.d(ex)
        null
    }
}
