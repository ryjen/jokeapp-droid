package com.github.ryjen.jokeapp.test.usecases

import com.github.ryjen.jokeapp.domain.repository.joke.JokeRepository
import com.github.ryjen.jokeapp.domain.usecase.AddFavoriteJoke
import com.github.ryjen.jokeapp.test.data.arch.module.fakeDatabaseModule
import com.github.ryjen.jokeapp.test.randomJoke
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject

class AddFavoriteJokeTest : KoinTest {

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(fakeDatabaseModule)
    }

    private val addFavJoke: AddFavoriteJoke by inject()
    private val repository: JokeRepository by inject()

    @ExperimentalCoroutinesApi
    @Test
    fun canAddFavoriteJoke() = runTest {
        val joke = randomJoke()
        addFavJoke(joke)
        val actual = repository.sync.getJoke(joke.id)
        assertThat(actual).isNotNull()
    }
}
