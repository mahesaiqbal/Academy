package com.mahesaiqbal.academy.ui.detail

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.mahesaiqbal.academy.academies.utils.FakeDataDummy
import com.mahesaiqbal.academy.data.source.local.entity.CourseEntity
import org.junit.After
import org.junit.Before

import org.junit.Rule
import org.junit.runner.RunWith
import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import com.mahesaiqbal.academy.R
import com.mahesaiqbal.academy.academies.utils.RecyclerViewItemCountAssertion
import org.junit.Test


@RunWith(AndroidJUnit4::class)
class DetailCourseActivityTest {

    val dummyCourse: CourseEntity = FakeDataDummy.generateDummyCourses().get(0)

    @Rule
    @JvmField
    var activityRule: ActivityTestRule<DetailCourseActivity> =
        object : ActivityTestRule<DetailCourseActivity>(DetailCourseActivity::class.java) {
            override fun getActivityIntent(): Intent {
                val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
                val result = Intent(targetContext, DetailCourseActivity::class.java)
                result.putExtra("extra_course", dummyCourse.courseId)
                return result
            }
        }


    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun loadCourses() {
        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_title)).check(matches(withText(dummyCourse.title)))
        onView(withId(R.id.text_date)).check(matches(isDisplayed()))
        onView(withId(R.id.text_date)).check(matches(withText("Deadline %s".format(dummyCourse.deadline))))
    }

    @Test
    fun loadModules() {
        onView(withId(R.id.rv_module)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_module)).check(RecyclerViewItemCountAssertion(7))
    }
}