package com.github.ryjen.jokeapp

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import com.github.ryjen.jokeapp.meta.modules.fakeAppModules
import com.github.ryjen.jokeapp.ui.MainActivity
import com.github.ryjen.jokeapp.ui.MainScreen
import com.github.ryjen.jokeapp.ui.theme.BlueTheme
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule

/**
 * Test the main activity
 */
class MainActivityTest : KoinTest {
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

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.github.ryjen.jokeapp", appContext.packageName)
    }

    @Test
    fun testOpensFavouriteList() {

        composeTestRule.setContent {
            BlueTheme {
                MainScreen()
            }
        }
        composeTestRule.onNodeWithTag("favorites").performClick()

        composeTestRule.onNodeWithTag("favoritesList").assertIsDisplayed()
    }
}
