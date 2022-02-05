package com.github.ryjen.jokeapp.data.storage

import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.meta.modules.fakeDatabaseModule
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
        printLogger()
        modules(fakeDatabaseModule)
    }

    private val jokeDao: JokeDao by inject()

    @Test
    fun testInsertJoke() = runTest {
        val expected = Joke(
            id = "123",
            content = "testing 123",
        )
        jokeDao.insertJokes(expected)
        val joke = jokeDao.getJoke(expected.id)
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
        jokeDao.insertJokes(expected)
        jokeDao.deleteJokes(expected)
        val joke = jokeDao.getJoke(expected.id)
        assertThat(joke).isNull()
    }

}
