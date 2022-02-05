package com.github.ryjen.jokeapp.ui.favorites

import com.github.ryjen.jokeapp.data.repository.JokeRepository
import com.github.ryjen.jokeapp.data.storage.randomJoke
import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.meta.modules.fakeAppModules
import com.github.ryjen.jokeapp.ui.jokes.favourites.FavoritesActions
import com.github.ryjen.jokeapp.ui.jokes.favourites.FavoritesViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject

@ExperimentalCoroutinesApi
class FavoritesViewModelTest : KoinTest {

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(fakeAppModules)
    }

    private val viewModel: FavoritesViewModel by inject()
    private val repo: JokeRepository by inject()

    lateinit var joke: Joke

    @Before
    fun setUp() {
        joke = randomJoke()
    }

    @Test
    fun testRemoveJoke() = runTest {
        repo.addFavorite(joke)
        val previous = viewModel.state.value.copy()
        viewModel.onAction(FavoritesActions.Remove(joke))
    }

    @Test
    fun testUndoRemoveJoke() = runTest {
        viewModel.onAction(FavoritesActions.Add(joke))
    }
}
