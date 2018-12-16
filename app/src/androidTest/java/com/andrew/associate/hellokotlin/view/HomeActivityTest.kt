package com.andrew.associate.hellokotlin.view

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.andrew.associate.hellokotlin.R.id.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityTest{
    @Rule
    @JvmField var activityTestRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun testRecyclerViewPrevGameBehaviour(){
        Thread.sleep(3000)
        onView(withId(rv_game_prev)).check(matches(isDisplayed()))
        onView(withId(rv_game_prev)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(withId(rv_game_prev)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))
    }

    @Test
    fun testRecyclerViewNextGameBehaviour(){
        Thread.sleep(3000)
        onView(withId(bottom_navigation_home)).check(matches(isDisplayed()))
        onView(withId(next_match)).perform(click())

        Thread.sleep(3000)
        onView(withId(rv_game_next)).check(matches(isDisplayed()))
        onView(withId(rv_game_next)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(withId(rv_game_next)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))
    }

    @Test
    fun testAppBehaviour(){

        Thread.sleep(3000)
        onView(withId(rv_game_prev)).check(matches(isDisplayed()))
        onView(withId(rv_game_prev)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(2))
        onView(withId(rv_game_prev)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))

        onView(withText("Match Detail")).check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())
        onView(withText("Added to Your Favorite Game")).check(matches(isDisplayed()))
        pressBack()

        Thread.sleep(1000)
        onView(withId(rv_game_prev)).check(matches(isDisplayed()))
        onView(withId(rv_game_prev)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(2))
        onView(withId(rv_game_prev)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))

        onView(withText("Match Detail")).check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())
        onView(withText("Removed from your Favorite Game")).check(matches(isDisplayed()))
        pressBack()
    }
}