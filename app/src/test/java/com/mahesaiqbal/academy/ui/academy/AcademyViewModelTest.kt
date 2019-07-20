package com.mahesaiqbal.academy.ui.academy

import com.mahesaiqbal.academy.data.CourseEntity
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class AcademyViewModelTest {

    var viewModel: AcademyViewModel? = null

    @Before
    fun setUp() {
        viewModel = AcademyViewModel()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getCourses() {
        val courseEntities: List<CourseEntity> = viewModel!!.getCourses()
        assertNotNull(courseEntities)
        assertEquals(5, courseEntities.size)
    }
}