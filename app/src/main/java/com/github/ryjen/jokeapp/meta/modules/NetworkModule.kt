package com.github.ryjen.jokeapp.meta.modules

import com.github.ryjen.jokeapp.data.api.JokeService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal val networkModule = module {

    single {

        val logger =
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()

        Retrofit.Builder()
            .baseUrl("https://icanhazdadjoke.com")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JokeService::class.java)
    }
}
