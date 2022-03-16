package com.github.ryjen.jokeapp.test

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import com.github.ryjen.jokeapp.MainActivity
import com.github.ryjen.jokeapp.test.module.fakeAppModules
import com.github.ryjen.jokeapp.ui.MainScreen
import com.github.ryjen.jokeapp.ui.theme.MainTheme
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule

/**
 * Test the main activity
 */
class MainActivityTest : KoinTest {
    @get:Rule
    val activityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(fakeAppModules)
    }

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.github.ryjen.jokeapp", appContext.packageName)
    }

    @Test
    fun testOpensFavouriteList() {

        composeTestRule.setContent {
            MainTheme {
                MainScreen()
            }
        }
        composeTestRule.onNodeWithTag("favorites").performClick()

        composeTestRule.onNodeWithTag("favoritesList").assertIsDisplayed()
    }
}
