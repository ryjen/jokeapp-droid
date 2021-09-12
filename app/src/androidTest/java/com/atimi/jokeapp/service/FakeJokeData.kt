package com.atimi.jokeapp.service

import com.atimi.jokeapp.model.Joke

class FakeJokeData {

    // fake data
    private val data = mutableListOf(
        Joke("111", "Testing 111", 200),
        Joke("222", "Testing 222", 200),
        Joke("333", "Testing 333", 200)
    )

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