package com.github.ryjen.jokeapp.test.ui.favorites

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.github.javafaker.Faker
import com.github.ryjen.jokeapp.MainActivity
import com.github.ryjen.jokeapp.data.repository.joke.JokeRepository
import com.github.ryjen.jokeapp.domain.model.Joke
import com.github.ryjen.jokeapp.test.module.fakeAppModules
import com.github.ryjen.jokeapp.ui.jokes.favourites.FavoritesScreen
import com.github.ryjen.jokeapp.ui.jokes.favourites.FavoritesViewModel
import com.github.ryjen.jokeapp.ui.theme.MainTheme
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject

class FavoritesScreenTest : KoinTest {

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

    private val faker = Faker()

    private val repo: JokeRepository by inject()

    private val viewModel: FavoritesViewModel by inject()

    private var joke = mockk<Joke>(relaxed = true)

    @Before
    fun setUp() {

        every { joke.content } returns faker.lorem().paragraph(4)
        every { joke.id } returns faker.idNumber().valid()

        runBlocking {
            repo.addFavorite(joke)
        }

        composeTestRule.setContent {
            MainTheme {
                FavoritesScreen(viewModel)
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
