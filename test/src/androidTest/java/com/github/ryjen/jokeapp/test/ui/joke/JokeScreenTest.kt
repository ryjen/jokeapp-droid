package com.github.ryjen.jokeapp.test.ui.joke

import android.content.Intent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.github.ryjen.jokeapp.MainActivity
import com.github.ryjen.jokeapp.data.repository.joke.JokeRepository
import com.github.ryjen.jokeapp.test.FakeJokeData
import com.github.ryjen.jokeapp.test.module.fakeAppModules
import com.github.ryjen.jokeapp.test.randomJoke
import com.github.ryjen.jokeapp.ui.jokes.random.RandomJokeScreen
import com.github.ryjen.jokeapp.ui.jokes.random.RandomJokeViewModel
import com.github.ryjen.jokeapp.ui.theme.MainTheme
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject

class JokeScreenTest : KoinTest {

    private val activityTestRule = ActivityScenarioRule(MainActivity::class.java)

    private val koinTestRule = KoinTestRule.create {
        modules(fakeAppModules)
    }

    private val composeTestRule = createComposeRule()

    @get:Rule
    val rule: RuleChain = RuleChain
        .outerRule(koinTestRule)
        .around(activityTestRule)
        .around(composeTestRule)

    private val repo: JokeRepository by inject()

    private val viewModel: RandomJokeViewModel by inject()

    companion object {

        @BeforeClass
        @JvmStatic
        fun startUp() {
            Intents.init()
        }
    }

    @Before
    fun setUp() {
        val joke = randomJoke()

        runBlocking {
            repo.addFavorite(joke)
        }
        composeTestRule.setContent {
            MainTheme {
                RandomJokeScreen(viewModel)
            }
        }
    }

    @Test
    fun testShareJoke() {
        composeTestRule.onNodeWithTag("share").performClick()

        intended(hasAction(Intent.ACTION_CHOOSER))
    }

    @Test
    fun testFavourites() {
        composeTestRule.onNodeWithTag("add").performClick()

        composeTestRule.onNodeWithText("added").assertIsDisplayed()
    }
}
