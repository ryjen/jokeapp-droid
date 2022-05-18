package com.github.ryjen.jokeapp.data.mapping

import com.github.ryjen.jokeapp.data.model.Joke
import com.github.ryjen.jokeapp.data.model.JokeResponse
import org.junit.Test
import java.util.*
import kotlin.test.assertEquals
import com.github.ryjen.jokeapp.domain.model.Joke as DomainJoke

class JokeMapperTest {
    @Test
    fun `can convert data to domain`() {
        val data = Joke("id", "text", Date(), true)

        val actual = JokeMapper(data)

        assertEquals(
            expected = "id",
            actual = actual.id
        )

        assertEquals(
            expected = "text",
            actual = actual.content
        )

        assertEquals(
            expected = true,
            actual = actual.isFavorite
        )
    }

    @Test
    fun `can convert network response to domain`() {
        val data = JokeResponse("id", "text", 200)

        val actual = JokeMapper(data)

        assertEquals(
            expected = "id",
            actual = actual.id
        )

        assertEquals(
            expected = "text",
            actual = actual.content
        )

        assertEquals(
            expected = false,
            actual = actual.isFavorite
        )
    }

    @Test
    fun `can convert domain to data`() {
        val domain = DomainJoke("id", "test", Date(), true)

        val actual = JokeMapper(domain)

        assertEquals(
            expected = "id",
            actual = actual.id
        )

        assertEquals(
            expected = "test",
            actual = actual.content
        )

        assertEquals(
            expected = true,
            actual = actual.isFavorite
        )
    }
}
