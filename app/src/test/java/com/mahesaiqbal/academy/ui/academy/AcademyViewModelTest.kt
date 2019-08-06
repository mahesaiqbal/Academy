package com.mahesaiqbal.academy.ui.academy

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
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

class AcademyViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

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
        val dummyCourse: MutableLiveData<List<CourseEntity>> = MutableLiveData()
        dummyCourse.value = FakeDataDummy.generateDummyCourses()

        `when`(academyRepository.getAllCourses()).thenReturn(dummyCourse)

        val observer: Observer<List<CourseEntity>> = mock(Observer::class.java) as Observer<List<CourseEntity>>

        viewModel?.getCourses()?.observeForever(observer)

        verify(academyRepository).getAllCourses()
    }
}