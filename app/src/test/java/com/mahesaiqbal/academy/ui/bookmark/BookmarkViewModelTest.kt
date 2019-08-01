package com.mahesaiqbal.academy.ui.bookmark

import com.mahesaiqbal.academy.data.source.local.entity.CourseEntity
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class BookmarkViewModelTest {

    var viewModel: BookmarkViewModel? = null

    @Before
    fun setUp() {
        viewModel = BookmarkViewModel(mAcademyRepository)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getBookmark() {
        val courseEntities: List<CourseEntity> = viewModel!!.getBookmarks()
        assertNotNull(courseEntities)
        assertEquals(5, courseEntities.size)
    }
}