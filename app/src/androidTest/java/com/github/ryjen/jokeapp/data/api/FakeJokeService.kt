package com.github.ryjen.jokeapp.data.api

import com.github.ryjen.jokeapp.domain.model.Joke
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// A fake remote service
class FakeJokeService : Dispatcher() {
    private var server = MockWebServer()

    var data = FakeJokeData()

    init {
        server.dispatcher = this
        server.start()
    }

    // get a client to the service
    fun client(): JokeService {

        val url = server.url("/")

        val logger =
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()

        return Retrofit.Builder()
            .baseUrl(url)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JokeService::class.java)
    }

    fun stop() = server.shutdown()

    // build a response in the service
    fun response() = Builder(server)

    class Builder(private val server: MockWebServer) {
        private val response = MockResponse()
        private val gson = Gson()

        fun joke(joke: Joke): Builder {
            response.addHeader("Content-Type", "application/json")
            response.setBody(gson.toJson(joke))
            return this
        }

        fun code(code: Int): Builder {
            response.setResponseCode(code)
            return this
        }

        fun header(name: String, value: String): Builder {
            response.addHeader(name, value)
            return this
        }

        // add to the next response
        fun enqueue() {
            server.enqueue(response)
        }

        fun build(): MockResponse {
            return response
        }
    }

    // dispatch a joke based on the request
    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path) {
            "/" -> response().code(200).joke(data.next()).build()
            else -> response().code(500).build()
        }
    }
}
