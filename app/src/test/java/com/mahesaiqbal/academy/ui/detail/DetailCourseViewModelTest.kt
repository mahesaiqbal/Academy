package com.mahesaiqbal.academy.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.mahesaiqbal.academy.data.source.AcademyRepository
import com.mahesaiqbal.academy.data.source.local.entity.CourseEntity
import org.junit.After
import org.junit.Before

import org.junit.Test
import com.mahesaiqbal.academy.utils.FakeDataDummy
import org.junit.Rule
import org.mockito.Mockito.*
import com.mahesaiqbal.academy.data.source.local.entity.CourseWithModule
import com.mahesaiqbal.academy.vo.Resource
import org.mockito.Mockito.`when`

class DetailCourseViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    var viewModel: DetailCourseViewModel? = null
    var academyRepository: AcademyRepository = mock(AcademyRepository::class.java)
    var dummyCourse: CourseEntity? = FakeDataDummy.generateDummyCourses()[0]
    var courseId = dummyCourse?.courseId

    @Before
    fun setUp() {
        viewModel = DetailCourseViewModel(academyRepository)
        viewModel!!.setCourseIdValue(courseId!!)
        viewModel!!.setBookmark()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getCourse() {
        val resource: Resource<CourseWithModule> =
            Resource.success(FakeDataDummy.generateDummyCourseWithModules(dummyCourse!!, true))
        val courseEntities = MutableLiveData<Resource<CourseWithModule>>()
        courseEntities.setValue(resource)

        `when`(academyRepository.getCourseWithModules(courseId!!)).thenReturn(courseEntities)

        val observer = mock(Observer::class.java) as Observer<Resource<CourseWithModule>>
        viewModel?.courseModule?.observeForever(observer)

        verify(observer).onChanged(resource)
    }
}