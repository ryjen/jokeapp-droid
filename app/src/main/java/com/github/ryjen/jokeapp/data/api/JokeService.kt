package com.github.ryjen.jokeapp.data.api

import com.github.ryjen.jokeapp.domain.model.Joke
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface JokeService {
    @GET("/")
    @Headers("Accept: application/json")
    suspend fun getRandomJoke(): Joke?

    @GET("/j/{id}")
    @Headers("Accept: application/json")
    suspend fun getJoke(@Path("id") id: String): Joke?
}
