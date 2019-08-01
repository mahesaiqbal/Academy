package com.mahesaiqbal.academy.ui.bookmark

import com.mahesaiqbal.academy.data.source.AcademyRepository
import com.mahesaiqbal.academy.data.source.local.entity.CourseEntity
import com.mahesaiqbal.academy.utils.FakeDataDummy
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.*

class BookmarkViewModelTest {

    var viewModel: BookmarkViewModel? = null
    var academyRepository: AcademyRepository = mock(AcademyRepository::class.java)

    @Before
    fun setUp() {
        viewModel = BookmarkViewModel(academyRepository)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getBookmark() {
        `when`(academyRepository.getBookmarkedCourses()).thenReturn(FakeDataDummy.generateDummyCourses())
        val courseEntities: List<CourseEntity> = viewModel!!.getBookmarks()
        verify(academyRepository).getBookmarkedCourses()
        assertNotNull(courseEntities)
        assertEquals(5, courseEntities.size)
    }
}