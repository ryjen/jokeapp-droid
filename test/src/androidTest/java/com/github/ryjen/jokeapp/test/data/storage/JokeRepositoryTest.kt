package com.github.ryjen.jokeapp.test.data.storage

import com.github.ryjen.jokeapp.data.repository.joke.JokeRepository
import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.test.FakeJokeData
import com.github.ryjen.jokeapp.test.data.arch.module.fakeDatabaseModule
import com.google.common.truth.Truth.assertThat
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject

@ExperimentalCoroutinesApi
class JokeRepositoryTest : KoinTest {

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(fakeDatabaseModule)
    }

    private val repo: JokeRepository by inject()
    private val data = FakeJokeData()

    @Before
    fun setup() {
        runBlocking {
            for (joke in data.all()) {
                repo.addFavorite(joke)
            }
        }
    }

    @Test
    fun testRemoveJokes() = runTest {
        val removed = repo.getJoke(data.randomId())

        if (removed != null) {
            repo.removeFavorite(removed)
            val joke = repo.getJoke(removed.id)
            assertThat(joke).isNull()
        }
    }

    @Test
    fun testAddJokes() = runTest {
        val expected = Joke("123", "Testing 123")
        repo.addFavorite(expected)
        val actual = repo.getJoke(expected.id)
        assertThat(actual).isNotNull()
        assertThat(actual?.id).isEqualTo(expected.id)
    }


    @Test
    fun testGetRandomJoke() = runTest {
        val joke = repo.getRandomJoke()
        assertThat(joke?.id).isEqualTo(data.current.id)
    }

    @Test
    fun testGetFavouriteJokes() = runTest {
        val jokes = repo.getFavoriteJokes().toList()
        assertThat(jokes.isEmpty()).isFalse()
    }

    @Test
    fun testGetJoke() = runTest {
        val joke = repo.getJoke(data.randomId())
        assertThat(joke).isNotNull()
        assertThat(joke?.created).isNotNull()
    }

    @Test
    fun testGetJokeNegative() = runTest {
        val r = mockk<JokeRepository>(relaxed = true)
        r.getJoke("arst1234")
        coVerify(exactly = 1) { r.local.getJoke(any()) }
        coVerify(exactly = 1) { r.remote.getJoke(any()) }
    }
}
