package com.github.ryjen.jokeapp.storage

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.ryjen.jokeapp.MainCoroutineRule
import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.runBlockingTest
import com.github.ryjen.jokeapp.data.api.FakeJokeService
import com.github.ryjen.jokeapp.data.repository.JokeRepository
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import javax.inject.Inject

@HiltAndroidTest
@ExperimentalCoroutinesApi
class JokeRepositoryTest {
    private val hiltRule = HiltAndroidRule(this)
    private val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val coroutineRule = MainCoroutineRule()

    @get:Rule
    val rule: RuleChain = RuleChain
        .outerRule(hiltRule)
        .around(instantTaskExecutorRule)
        .around(coroutineRule)

    @Inject
    lateinit var service: FakeJokeService

    @Inject
    lateinit var repo: JokeRepository

    @Before
    fun setup() {
        hiltRule.inject()

        runBlocking {
            repo.addJokes(*service.data.all())
        }
    }

    @Test
    fun testRemoveJokes() = coroutineRule.runBlockingTest {
        val removed = repo.getJoke(service.data.randomId())

        if (removed != null) {
            repo.removeJokes(removed)
            val joke = repo.getJoke(removed.id)
            assertThat(joke).isNull()
        }
    }

    @Test
    fun testAddJokes() = coroutineRule.runBlockingTest {
        val expected = Joke("123", "Testing 123", 210)
        repo.addJokes(expected)
        val actual = repo.getJoke(expected.id)
        assertThat(actual).isNotNull()
        assertThat(actual?.id).isEqualTo(expected.id)
    }


    @Test
    fun testGetRandomJoke() = coroutineRule.runBlockingTest {
//        val joke = repo.getRandomJoke()
//        assertThat(joke?.id).isEqualTo(service.data.current.id)
    }

    @Test
    fun testGetFavouriteJokes() = coroutineRule.runBlockingTest {
        val jokes = repo.getFavouriteJokes()
        assertThat(jokes.isEmpty()).isFalse()
    }

    @Test
    fun testGetJoke() = coroutineRule.runBlockingTest {
        val joke = repo.getJoke(service.data.randomId())
        assertThat(joke).isNotNull()
        assertThat(joke?.created).isNotNull()
    }
}
