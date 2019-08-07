package com.mahesaiqbal.academy.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.mahesaiqbal.academy.R
import com.mahesaiqbal.academy.ui.home.HomeActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AcademyTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(HomeActivity::class.java)

    @Before
    fun setUp() {

    }

    @After
    fun tearDown() {

    }

    @Test
    fun toDetailActivityTest() {
        try {
            Thread.sleep(3000)
        } catch(e: InterruptedException) {
            e.printStackTrace()
        }

        onView(withId(R.id.rv_academy)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_academy)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        try {
            Thread.sleep(3000)
        } catch(e: InterruptedException) {
            e.printStackTrace()
        }

        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_title)).check(matches(withText("Menjadi Android Developer Expert")))
    }

    @Test
    fun toReaderActivityTest() {
        try {
            Thread.sleep(3000)
        } catch(e: InterruptedException) {
            e.printStackTrace()
        }

        onView(withId(R.id.rv_academy)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_academy)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        try {
            Thread.sleep(3000)
        } catch(e: InterruptedException) {
            e.printStackTrace()
        }

        onView(withId(R.id.btn_start)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_start)).perform(click())

        try {
            Thread.sleep(3000)
        } catch(e: InterruptedException) {
            e.printStackTrace()
        }

        onView(withId(R.id.frame_container)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_module)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_module)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        try {
            Thread.sleep(3000)
        } catch(e: InterruptedException) {
            e.printStackTrace()
        }

        onView(withId(R.id.web_view)).check(matches(isDisplayed()))
    }
}