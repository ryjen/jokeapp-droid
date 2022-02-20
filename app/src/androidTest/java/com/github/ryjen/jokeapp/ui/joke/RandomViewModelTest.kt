package com.github.ryjen.jokeapp.ui.joke

import com.github.ryjen.jokeapp.data.repository.JokeRepository
import com.github.ryjen.jokeapp.data.storage.randomJoke
import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.meta.arch.modules.fakeAppModules
import com.github.ryjen.jokeapp.ui.jokes.random.RandomViewModel
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject

@ExperimentalCoroutinesApi
class RandomViewModelTest : KoinTest {

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(fakeAppModules)
    }

    private val viewModel: RandomViewModel by inject()

    private val repo: JokeRepository by inject()

    lateinit var joke: Joke

    @Before
    fun setUp() {
        joke = randomJoke()
    }

    @Test
    fun testAddJokeToFavourites() = runTest {
        viewModel.addJokeToFavorites(joke)
        val actual = repo.getJoke(joke.id)
        assertThat(actual).isNotNull()
    }


    @Test
    fun testRemoveJokeFromFavourites() = runTest {
        viewModel.removeJokeFromFavorites(joke)

        val actual = repo.getJoke(joke.id)
        assertThat(actual).isNull()
    }


    @Test
    fun testRefreshJoke() = runTest {

        val previous = viewModel.currentJoke

        viewModel.refreshJoke()

        assertThat(viewModel.currentJoke?.id).isNotEqualTo(previous?.id)
    }
}
