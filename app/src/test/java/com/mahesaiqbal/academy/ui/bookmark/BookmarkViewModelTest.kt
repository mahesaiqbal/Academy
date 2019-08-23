package com.mahesaiqbal.academy.ui.bookmark

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.mahesaiqbal.academy.data.source.AcademyRepository
import com.mahesaiqbal.academy.data.source.local.entity.CourseEntity
import com.mahesaiqbal.academy.utils.FakeDataDummy
import com.mahesaiqbal.academy.vo.Resource
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class BookmarkViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

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
        val resource: Resource<List<CourseEntity>> = Resource.success(FakeDataDummy.generateDummyCourses())
        val dummyCourses = MutableLiveData<Resource<List<CourseEntity>>>()
        dummyCourses.setValue(resource)

        `when`(academyRepository.getBookmarkedCourses()).thenReturn(dummyCourses)

        val observer = mock(Observer::class.java) as Observer<Resource<List<CourseEntity>>>

        viewModel?.getBookmarks()?.observeForever(observer)

        verify(observer).onChanged(resource)
    }
}