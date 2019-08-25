package com.mahesaiqbal.academy.ui.bookmark

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.mahesaiqbal.academy.data.source.AcademyRepository
import com.mahesaiqbal.academy.data.source.local.entity.CourseEntity
import com.mahesaiqbal.academy.vo.Resource
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import androidx.paging.PagedList
import org.mockito.Mockito.`when`

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
        val dummyCourse = MutableLiveData<Resource<PagedList<CourseEntity>>>()
        val pagedList = mock(PagedList::class.java) as PagedList<CourseEntity>

        dummyCourse.setValue(Resource.success(pagedList))

        `when`<LiveData<Resource<PagedList<CourseEntity>>>>(academyRepository.getBookmarkedCoursesPaged()).thenReturn(
            dummyCourse
        )

        val observer = mock(Observer::class.java) as Observer<Resource<PagedList<CourseEntity>>>

        viewModel?.getBookmarks()?.observeForever(observer)

        verify(observer).onChanged(Resource.success(pagedList))
    }
}