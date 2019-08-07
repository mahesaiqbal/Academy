package com.mahesaiqbal.academy.ui.academy

import androidx.test.rule.ActivityTestRule

import com.mahesaiqbal.academy.R
import com.mahesaiqbal.academy.academies.utils.RecyclerViewItemCountAssertion
import com.mahesaiqbal.academy.testing.SingleFragmentActivity

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AcademyFragmentTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(SingleFragmentActivity::class.java)
    val academyFragment = AcademyFragment()

    @Before
    fun setUp() {
        activityRule.activity.setFragment(academyFragment)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun loadCourses() {
        try {
            Thread.sleep(3000)
        } catch(e: InterruptedException) {
            e.printStackTrace()
        }

        onView(withId(R.id.rv_academy)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_academy)).check(RecyclerViewItemCountAssertion(5))
    }
}