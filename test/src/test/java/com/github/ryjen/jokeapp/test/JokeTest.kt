package com.github.ryjen.jokeapp.test

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.koin.test.KoinTest

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class JokeTest : KoinTest {

    @Test
    fun `can be a data object`() {
        val joke = randomJoke()
        assertThat(joke.id).isNotEmpty()
        assertThat(joke.content).isNotEmpty()
        assertThat(joke.created).isNull()
        assertThat(joke.isFavorite).isFalse()
    }
}
