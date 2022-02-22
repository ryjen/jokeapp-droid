package com.github.ryjen.jokeapp.test.data.storage

import com.github.ryjen.jokeapp.data.model.Joke
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
            id = "123",
            content = "testing 123",
        )
        db.asyncJokeDao().insertJokes(expected)
        val joke = db.asyncJokeDao().getJoke(expected.id)
        assertThat(joke).isNotNull()
        assertThat(joke?.id).isEqualTo(expected.id)
        assertThat(joke?.created).isNotNull()
    }

    @Test
    fun testDeleteJoke() = runTest {
        val expected = Joke(
            id = "123",
            content = "testing 123",
        )
        db.asyncJokeDao().insertJokes(expected)
        db.asyncJokeDao().deleteJokes(expected)
        val joke = db.asyncJokeDao().getJoke(expected.id)
        assertThat(joke).isNull()
    }

}
