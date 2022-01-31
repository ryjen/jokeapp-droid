package com.github.ryjen.jokeapp.ui.joke

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.ryjen.jokeapp.meta.coroutines.DispatcherProvider
import com.github.ryjen.jokeapp.MainCoroutineRule
import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.runBlockingTest
import com.github.ryjen.jokeapp.data.repository.JokeRepository
import com.github.ryjen.jokeapp.ui.jokes.random.JokeViewModel
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import javax.inject.Inject

@HiltAndroidTest
@ExperimentalCoroutinesApi
class JokeViewModelTest {

    private val hiltRule = HiltAndroidRule(this)
    private val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val coroutineRule = MainCoroutineRule()

    @get:Rule
    val rule: RuleChain = RuleChain
        .outerRule(hiltRule)
        .around(instantTaskExecutorRule)
        .around(coroutineRule)

    lateinit var viewModel: JokeViewModel

    @Inject
    lateinit var repo: JokeRepository

    @Inject
    lateinit var dispatchers: DispatcherProvider

    lateinit var joke: Joke

    @Before
    fun setUp() {
        hiltRule.inject()

        joke = Joke("321", "Testing 321", 200)

        viewModel = JokeViewModel(repo, dispatchers)

        viewModel.data.value = joke
    }

    @Test
    fun testAddJokeToFavourites() = coroutineRule.runBlockingTest {
        viewModel.addJokeToFavourites()

        assertThat(viewModel.data.value?.created).isNotNull()
    }


    @Test
    fun testRemoveJokeFromFavourites() = coroutineRule.runBlockingTest {
        viewModel.removeJokeFromFavourites()

        assertThat(viewModel.data.value?.created).isNull()
    }


    @Test
    fun testRefreshJoke() = coroutineRule.runBlockingTest {
//        viewModel.refreshJoke()
//        
//        assertThat(viewModel.data.value?.id).isNotEqualTo(joke.id)
    }
}
