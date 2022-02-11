package com.github.ryjen.jokeapp.data.api

import com.github.ryjen.jokeapp.domain.model.Joke
import io.ktor.client.*
import io.ktor.client.request.*
import timber.log.Timber

class JokeService(
    private val client: HttpClient
) {
    companion object {
        const val BASE_URL = "https://icanhazdadjoke.com"
    }
    suspend fun getRandomJoke(): Joke? = try {
        client.get<Joke> {
            url(BASE_URL)
        }
    } catch (ex: Throwable) {
        Timber.d(ex)
        null
    }

    suspend fun getJoke(id: String): Joke? = try {
        client.get {
            url("$BASE_URL/j/$id")
        }
    } catch (ex: Throwable) {
        Timber.d(ex)
        null
    }
}
