package com.github.ryjen.jokeapp.test

import com.github.ryjen.jokeapp.data.model.toData
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import com.github.ryjen.jokeapp.data.model.Joke as DataJoke

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class JokeTest {
    @Test
    fun `can be a data object`() {
        val joke = randomJoke()
        assertThat(joke.id).isNotEmpty()
        assertThat(joke.content).isNotEmpty()
        assertThat(joke.created).isNull()
        assertThat(joke.isFavorite).isFalse()
        assertThat(joke.toData()).isInstanceOf(DataJoke::class.java)
    }
}
