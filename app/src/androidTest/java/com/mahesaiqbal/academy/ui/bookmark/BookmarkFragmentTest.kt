package com.mahesaiqbal.academy.ui.bookmark

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.mahesaiqbal.academy.R
import com.mahesaiqbal.academy.academies.utils.RecyclerViewItemCountAssertion
import com.mahesaiqbal.academy.testing.SingleFragmentActivity
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BookmarkFragmentTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(SingleFragmentActivity::class.java)
    var bookmarkFragment = BookmarkFragment()

    @Before
    fun setUp() {
        activityRule.activity.setFragment(bookmarkFragment)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun loadBookmarks() {
        try {
            Thread.sleep(3000)
        } catch(e: InterruptedException) {
            e.printStackTrace()
        }

        onView(withId(R.id.rv_bookmark)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_bookmark)).check(RecyclerViewItemCountAssertion(5))
    }
}