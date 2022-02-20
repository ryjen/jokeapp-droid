package com.github.ryjen.jokeapp.ui.favorites

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.espresso.intent.Intents
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.github.ryjen.jokeapp.data.repository.JokeRepository
import com.github.ryjen.jokeapp.data.storage.randomJoke
import com.github.ryjen.jokeapp.meta.arch.modules.fakeAppModules
import com.github.ryjen.jokeapp.ui.MainActivity
import com.github.ryjen.jokeapp.ui.jokes.favourites.FavoritesScreen
import com.github.ryjen.jokeapp.ui.jokes.favourites.FavoritesViewModel
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

class FavoritesScreenTest : KoinTest {

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

    private val repo: JokeRepository by inject()

    private val viewModel: FavoritesViewModel by inject()

    private var joke = randomJoke()

    companion object {
        @BeforeClass
        @JvmStatic
        fun startUp() {
            Intents.init()
        }
    }

    @Before
    fun setUp() {

        runBlocking {
            repo.addFavorite(joke)
        }

        composeTestRule.setContent {
            BlueTheme {
                FavoritesScreen()
            }
        }
    }

    @Test
    fun testEmpty() {
        with(composeTestRule) {
            onNodeWithTag("remove").performClick()
            onNodeWithText("no favorites").assertIsDisplayed()
        }
    }

    @Test
    fun testRemoveJoke() {
        with(composeTestRule) {
            onNodeWithTag("remove").performClick()
            onNodeWithText(joke.content).assertDoesNotExist()
        }
    }
}
