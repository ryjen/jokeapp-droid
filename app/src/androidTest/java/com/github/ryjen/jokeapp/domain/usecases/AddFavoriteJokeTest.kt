package com.github.ryjen.jokeapp.domain.usecases

import com.github.ryjen.jokeapp.data.repository.JokeRepository
import com.github.ryjen.jokeapp.data.storage.randomJoke
import com.github.ryjen.jokeapp.domain.usecase.AddFavoriteJoke
import com.github.ryjen.jokeapp.meta.arch.modules.fakeDatabaseModule
import com.github.ryjen.jokeapp.meta.arch.modules.fakeNetworkModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import kotlin.test.assertTrue

class AddFavoriteJokeTest : KoinTest {

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(fakeDatabaseModule, fakeNetworkModule)
    }

    private val addFavJoke: AddFavoriteJoke by inject()
    private val repository: JokeRepository by inject()

    @ExperimentalCoroutinesApi
    @Test
    fun canAddFavoriteJoke() = runTest {
        val joke = randomJoke()
        addFavJoke(joke)
        assertTrue(
            repository.getFavoriteJokes().toList().isNotEmpty()
        )
    }
}
