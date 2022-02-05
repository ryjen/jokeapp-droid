package com.github.ryjen.jokeapp.data.storage

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.github.ryjen.jokeapp.domain.model.Joke
import kotlin.random.Random

private val rand = Random(System.currentTimeMillis())

fun randomJoke(): Joke {
    val id = rand.nextInt(1000, 9999)
    val content = LoremIpsum(rand.nextInt(20, 80)).values
        .joinToString("\n")
    return Joke(id.toString(), content)
}

class FakeJokeData {

    // fake data
    private val data = (1..15).fold(mutableListOf<Joke>()) { lst, _ ->
        lst.add(randomJoke())
        lst
    }

    fun all(): Array<Joke> = data.toTypedArray()

    // have a current joke
    private var currentIndex: Int = -1

    val current: Joke
        get() = if (currentIndex == -1) {
            data[0]
        } else {
            data[currentIndex]
        }

    // get a random existing joke id
    fun randomId(): String = data.random().id

    // gets the next joke in a circular buffer
    fun next(): Joke {
        if (++currentIndex >= data.size) {
            currentIndex = 0
        }
        return data[currentIndex]
    }

    fun add(item: Joke) {
        data.add(item)
    }
}
