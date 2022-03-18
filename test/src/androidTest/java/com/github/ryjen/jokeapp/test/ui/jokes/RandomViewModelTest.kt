package com.github.ryjen.jokeapp.test.ui.jokes

import com.github.ryjen.jokeapp.data.repository.joke.JokeRepository
import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.test.module.fakeAppModules
import com.github.ryjen.jokeapp.test.randomJoke
import com.github.ryjen.jokeapp.ui.jokes.random.RandomJokeAction
import com.github.ryjen.jokeapp.ui.jokes.random.RandomJokeViewModel
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
        modules(fakeAppModules)
    }

    private val viewModel: RandomJokeViewModel by inject()

    private val repo: JokeRepository by inject()

    lateinit var joke: Joke

    @Before
    fun setUp() {
        joke = randomJoke()
    }

    @Test
    fun testAddJokeToFavourites() = runTest {
        viewModel.dispatch(RandomJokeAction.Favorite(joke))
        val actual = repo.getJoke(joke.id)
        assertThat(actual).isNotNull()
    }


    @Test
    fun testRemoveJokeFromFavourites() = runTest {
        viewModel.dispatch(RandomJokeAction.UnFavorite(joke))

        val actual = repo.getJoke(joke.id)
        assertThat(actual).isNull()
    }


    @Test
    fun testRefreshJoke() {

        viewModel.dispatch(RandomJokeAction.Refresh(joke))

        // FIXME: actual test
    }
}
