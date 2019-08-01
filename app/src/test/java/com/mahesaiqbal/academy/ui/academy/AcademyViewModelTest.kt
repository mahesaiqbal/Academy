package com.mahesaiqbal.academy.ui.academy

import com.mahesaiqbal.academy.data.source.AcademyRepository
import com.mahesaiqbal.academy.data.source.local.entity.CourseEntity
import com.mahesaiqbal.academy.utils.FakeDataDummy
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*


class AcademyViewModelTest {

    var viewModel: AcademyViewModel? = null
    var academyRepository: AcademyRepository = mock(AcademyRepository::class.java)

    @Before
    fun setUp() {
        viewModel = AcademyViewModel(academyRepository)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getCourses() {
        `when`(academyRepository.getAllCourses()).thenReturn(FakeDataDummy.generateDummyCourses())
        val courseEntities: List<CourseEntity> = viewModel!!.getCourses()
        verify(academyRepository).getAllCourses()
        assertNotNull(courseEntities)
        assertEquals(5, courseEntities.size)
    }
}