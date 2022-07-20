package com.github.ryjen.jokeapp.test.data.storage

import com.github.ryjen.jokeapp.data.storage.Joke
import com.github.ryjen.jokeapp.data.storage.JokeDatabase
import com.github.ryjen.jokeapp.test.data.arch.module.fakeDatabaseModule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject

@ExperimentalCoroutinesApi
class JokeDaoTest : KoinTest {

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(fakeDatabaseModule)
    }

    private val db: JokeDatabase by inject()

    @Test
    fun testInsertJoke() = runTest {
        val expected = Joke(
            id = 123,
            key = "123",
            content = "testing 123",
            created = null,
            isFavorite = false
        )
        db.jokeQueries.insert(
            key = expected.key,
            content = expected.content,
            expected.created,
            expected.isFavorite
        )
        val joke = db.jokeQueries.get(key = expected.key).executeAsOneOrNull()
        assertThat(joke).isNotNull()
        assertThat(joke?.key).isEqualTo(expected.key)
        assertThat(joke?.created).isNotNull()
    }

    @Test
    fun testDeleteJoke() = runTest {
        val expected = Joke(
            id = 123,
            key = "123",
            content = "testing 123",
            created = null,
            isFavorite = false
        )
        db.jokeQueries.insert(
            key = expected.key,
            content = expected.content,
            expected.created,
            expected.isFavorite
        )
        var joke = db.jokeQueries.get(expected.key).executeAsOneOrNull()
        assertThat(joke).isNotNull()
        db.jokeQueries.delete(expected.key)
        joke = db.jokeQueries.get(expected.key).executeAsOneOrNull()
        assertThat(joke).isNull()
    }

}
