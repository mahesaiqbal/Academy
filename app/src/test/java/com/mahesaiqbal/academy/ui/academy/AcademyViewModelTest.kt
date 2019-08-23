package com.mahesaiqbal.academy.ui.academy

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.mahesaiqbal.academy.data.source.AcademyRepository
import com.mahesaiqbal.academy.data.source.local.entity.CourseEntity
import com.mahesaiqbal.academy.utils.FakeDataDummy
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import com.mahesaiqbal.academy.vo.Resource
import org.mockito.Mockito.`when`

class AcademyViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    var viewModel: AcademyViewModel? = null
    var academyRepository: AcademyRepository = mock(AcademyRepository::class.java)

    private var USERNAME = "Dicoding"

    @Before
    fun setUp() {
        viewModel = AcademyViewModel(academyRepository)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getCourses() {
        val resource: Resource<List<CourseEntity>> = Resource.success(FakeDataDummy.generateDummyCourses())
        val dummyCourses = MutableLiveData<Resource<List<CourseEntity>>>()
        dummyCourses.setValue(resource)

        `when`(academyRepository.getAllCourses()).thenReturn(dummyCourses)

        val observer = mock(Observer::class.java) as Observer<Resource<List<CourseEntity>>>

        viewModel?.setUsername(USERNAME)

        viewModel?.courses?.observeForever(observer)

        verify(observer).onChanged(resource)
    }
}