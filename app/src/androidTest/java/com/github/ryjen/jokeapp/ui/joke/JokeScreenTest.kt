package com.github.ryjen.jokeapp.ui.joke

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
import com.github.ryjen.jokeapp.data.api.FakeJokeService
import com.github.ryjen.jokeapp.data.repository.JokeRepository
import com.github.ryjen.jokeapp.data.storage.randomJoke
import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.meta.arch.modules.fakeAppModules
import com.github.ryjen.jokeapp.ui.MainActivity
import com.github.ryjen.jokeapp.ui.jokes.random.JokeScreen
import com.github.ryjen.jokeapp.ui.theme.BlueTheme
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
        printLogger()
        modules(fakeAppModules)
    }

    private val composeTestRule = createComposeRule()

    @get:Rule
    val rule: RuleChain = RuleChain
        .outerRule(koinTestRule)
        .around(activityTestRule)
        .around(composeTestRule)

    private val service: FakeJokeService by inject()

    private val repo: JokeRepository by inject()

    private lateinit var joke: Joke

    companion object {

        @BeforeClass
        @JvmStatic
        fun startUp() {
            Intents.init()
        }
    }

    @Before
    fun setUp() {
        joke = randomJoke()

        runBlocking {
            repo.addFavorite(joke)
        }
        composeTestRule.setContent {
            BlueTheme {
                JokeScreen()
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
