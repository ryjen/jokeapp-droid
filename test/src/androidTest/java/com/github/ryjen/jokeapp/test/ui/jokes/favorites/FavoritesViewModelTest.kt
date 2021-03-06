package com.github.ryjen.jokeapp.test.ui.jokes.favorites

import com.github.ryjen.jokeapp.data.repository.joke.JokeRepository
import com.github.ryjen.jokeapp.test.module.fakeAppModules
import com.github.ryjen.jokeapp.test.randomJoke
import com.github.ryjen.jokeapp.ui.jokes.favourites.FavoritesAction
import com.github.ryjen.jokeapp.ui.jokes.favourites.FavoritesViewModel
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject

@ExperimentalCoroutinesApi
class FavoritesViewModelTest : KoinTest {

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(fakeAppModules)
    }

    private val viewModel: FavoritesViewModel by inject()
    private val repo: JokeRepository by inject()

    @Test
    fun testRemoveJoke() = runTest {
        val joke = randomJoke()
        repo.addFavorite(joke)
        viewModel.dispatch(FavoritesAction.Remove(joke))
        val exists = repo.getJoke(joke.id)
        assertNull(exists)
    }
}
