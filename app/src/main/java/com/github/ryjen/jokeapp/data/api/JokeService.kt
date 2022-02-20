package com.github.ryjen.jokeapp.data.api

import com.github.ryjen.jokeapp.domain.model.JokeResponse
import io.ktor.client.*
import io.ktor.client.request.*
import timber.log.Timber

class JokeService(
    private val client: HttpClient
) {
    companion object {
        const val BASE_URL = "https://icanhazdadjoke.com"
    }

    suspend fun getRandomJoke(): JokeResponse? = try {
        client.get<JokeResponse> {
            url(BASE_URL)
        }
    } catch (ex: Throwable) {
        Timber.d(ex)
        null
    }

    suspend fun getJoke(id: String): JokeResponse? = try {
        client.get {
            url("$BASE_URL/j/$id")
        }
    } catch (ex: Throwable) {
        Timber.d(ex)
        null
    }
}
