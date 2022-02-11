package com.github.ryjen.jokeapp.data.api

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json

fun createJokeClient(): HttpClient {

    val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        encodeDefaults = false
    }

    return HttpClient(Android) {
        // Logging
        install(Logging) {
            level = LogLevel.ALL
        }
        // JSON
        install(JsonFeature) {
            serializer = KotlinxSerializer(json)
            //or serializer = KotlinxSerializer()
        }
        // Timeout
        install(HttpTimeout) {
            requestTimeoutMillis = 15000L
            connectTimeoutMillis = 15000L
            socketTimeoutMillis = 15000L
        }
        // Apply to all requests
        defaultRequest {

            // Parameter("api_key", "some_api_key")
            // Content Type
            if (method != HttpMethod.Get) contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }
    }
}
