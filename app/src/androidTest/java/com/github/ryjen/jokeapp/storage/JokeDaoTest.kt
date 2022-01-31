package com.github.ryjen.jokeapp.storage

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.ryjen.jokeapp.data.storage.JokeDao
import com.github.ryjen.jokeapp.data.storage.JokeDatabase
import com.github.ryjen.jokeapp.domain.model.Joke
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import javax.inject.Inject

@HiltAndroidTest
@ExperimentalCoroutinesApi
class JokeDaoTest {
    private val hiltRule = HiltAndroidRule(this)
    private val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rule: RuleChain = RuleChain
        .outerRule(hiltRule)
        .around(instantTaskExecutorRule)

    @Inject
    lateinit var db: JokeDatabase

    @Inject
    lateinit var jokeDao: JokeDao

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun testInsertJoke() = runBlockingTest {
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
    fun testDeleteJoke() = runBlockingTest {
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
