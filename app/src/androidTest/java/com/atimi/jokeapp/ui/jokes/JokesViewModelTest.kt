package com.atimi.jokeapp.ui.jokes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.atimi.jokeapp.MainCoroutineRule
import com.atimi.jokeapp.model.Joke
import com.atimi.jokeapp.runBlockingTest
import com.atimi.jokeapp.storage.JokeRepository
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
class JokesViewModelTest {

    private val hiltRule = HiltAndroidRule(this)
    private val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val coroutineRule = MainCoroutineRule()

    @get:Rule
    val rule: RuleChain = RuleChain
        .outerRule(hiltRule)
        .around(instantTaskExecutorRule)
        .around(coroutineRule)

    @Inject
    lateinit var repo: JokeRepository

    lateinit var viewModel: JokesViewModel

    lateinit var joke: Joke

    @Before
    fun setUp() {
        hiltRule.inject()

        viewModel = JokesViewModel(repo)

        joke = Joke("4321", "Testing 1232", 200)

        viewModel.data.value = mutableListOf(joke)
    }

    @Test
    fun testRemoveJoke() = coroutineRule.runBlockingTest {
        val removed = viewModel.removeJoke(0)

        assertThat(removed).isNotNull()
        assertThat(removed?.id).isEqualTo(joke.id)
        assertThat(viewModel.data.value?.isEmpty()).isTrue()
    }

    @Test
    fun testUndoRemoveJoke() = coroutineRule.runBlockingTest {
        viewModel.undoRemovedJoke(0, joke)

        assertThat(viewModel.data.value?.isEmpty()).isFalse()
    }
}