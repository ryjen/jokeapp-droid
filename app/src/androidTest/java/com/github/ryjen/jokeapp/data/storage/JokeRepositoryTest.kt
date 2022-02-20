package com.github.ryjen.jokeapp.data.storage

import com.github.ryjen.jokeapp.data.api.FakeJokeService
import com.github.ryjen.jokeapp.data.repository.JokeRepository
import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.meta.arch.modules.fakeDatabaseModule
import com.github.ryjen.jokeapp.meta.arch.modules.fakeNetworkModule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
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
        printLogger()
        modules(fakeDatabaseModule, fakeNetworkModule)
    }

    private val service: FakeJokeService by inject()

    private val repo: JokeRepository by inject()

    @Before
    fun setup() {
        runBlocking {
            for (joke in service.data.all()) {
                repo.addFavorite(joke)
            }
        }
    }

    @Test
    fun testRemoveJokes() = runTest {
        val removed = repo.getJoke(service.data.randomId())

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
//        val joke = repo.getRandomJoke()
//        assertThat(joke?.id).isEqualTo(service.data.current.id)
    }

    @Test
    fun testGetFavouriteJokes() = runTest {
        val jokes = repo.getFavoriteJokes().toList()
        assertThat(jokes.isEmpty()).isFalse()
    }

    @Test
    fun testGetJoke() = runTest {
        val joke = repo.getJoke(service.data.randomId())
        assertThat(joke).isNotNull()
        assertThat(joke?.created).isNotNull()
    }
}
