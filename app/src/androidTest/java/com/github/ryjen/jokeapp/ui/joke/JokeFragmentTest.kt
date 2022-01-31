package com.github.ryjen.jokeapp.ui.joke

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.github.ryjen.jokeapp.ui.MainActivity
import com.github.ryjen.jokeapp.R
import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.data.api.FakeJokeService
import com.github.ryjen.jokeapp.data.repository.JokeRepository
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
class JokeFragmentTest {

    private val hiltRule = HiltAndroidRule(this)
    private val activityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    val rule: RuleChain = RuleChain
        .outerRule(hiltRule)
        .around(activityTestRule)

    @Inject
    lateinit var service: FakeJokeService

    @Inject
    lateinit var repo: JokeRepository

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
    }

    @Test
    fun testShareJoke() {
        onView(withId(R.id.action_share)).perform(click())

        intended(hasAction(Intent.ACTION_CHOOSER))
    }

    @Test
    fun testFavourites() {
        onView(withId(R.id.action_add)).perform(click())

        onView(withText(R.string.favourite_added)).check(matches(isDisplayed()))
    }
}
