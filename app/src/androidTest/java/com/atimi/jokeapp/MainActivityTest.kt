package com.atimi.jokeapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain

/**
 * Test the main activity
 */
@HiltAndroidTest
class MainActivityTest {
    private val hiltRule = HiltAndroidRule(this)
    private val activityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    val rule = RuleChain
        .outerRule(hiltRule)
        .around(activityTestRule)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.atimi.jokeapp", appContext.packageName)
    }

    @Test
    fun testOpensFavouriteList() {
        onView(withId(R.id.action_favourites)).perform(click())

        onView(withId(R.id.listLayout)).check(matches(isDisplayed()))
    }
}