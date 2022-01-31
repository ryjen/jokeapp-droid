package com.github.ryjen.jokeapp

import com.github.ryjen.jokeapp.domain.model.Joke
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

/**
 * Test joke data class
 */
class JokeUnitTest {
    private lateinit var joke: Joke

    @Before
    fun setUp() {
        joke = Joke("123", "testing 123", 200)
    }

    @Test
    fun testDefaults() {
        assertNull(joke.created)
        assertEquals(joke.id, "123")
    }
}
