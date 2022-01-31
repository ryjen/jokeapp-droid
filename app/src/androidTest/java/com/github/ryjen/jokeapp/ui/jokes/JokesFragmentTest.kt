package com.github.ryjen.jokeapp.ui.jokes

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.github.ryjen.jokeapp.ui.MainActivity
import com.github.ryjen.jokeapp.R
import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.data.repository.JokeRepository
import com.github.ryjen.jokeapp.ui.jokes.favourites.JokesViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import javax.inject.Inject

@HiltAndroidTest
class JokesFragmentTest {

    private val hiltRule = HiltAndroidRule(this)
    private val activityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    val rule: RuleChain = RuleChain
        .outerRule(hiltRule)
        .around(activityTestRule)

    @Inject
    lateinit var repo: JokeRepository

    lateinit var viewModel: JokesViewModel

    private var joke = Joke("435", "Testing 345", 200)

    companion object {

        @BeforeClass
        @JvmStatic
        fun startUp() {
            Intents.init()
        }
    }

    @Before
    fun setUp() {
        hiltRule.inject()

        runBlocking {
            repo.addJokes(joke)
        }

        viewModel = JokesViewModel(repo)
    }

    @Test
    fun testEmpty() {
        onView(withId(R.id.button_remove)).perform(click())
        onView(withText(R.string.no_favourites)).check(matches(isDisplayed()))
    }

    @Test
    fun testRemoveJoke() {
        onView(withId(R.id.button_remove)).perform(click())
        onView(withText(joke.joke)).check(doesNotExist())
    }
}
