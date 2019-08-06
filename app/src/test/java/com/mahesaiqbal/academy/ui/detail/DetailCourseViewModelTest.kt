package com.mahesaiqbal.academy.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.mahesaiqbal.academy.data.source.AcademyRepository
import com.mahesaiqbal.academy.data.source.local.entity.CourseEntity
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import com.mahesaiqbal.academy.data.source.local.entity.ModuleEntity
import com.mahesaiqbal.academy.utils.FakeDataDummy
import org.junit.Rule
import org.mockito.Mockito.*


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
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getCourse() {
        val courseEntities: MutableLiveData<CourseEntity> = MutableLiveData()
        courseEntities.value = dummyCourse

        `when`(academyRepository.getCourseWithModules(courseId!!)).thenReturn(courseEntities)

        val observer: Observer<CourseEntity> = mock(Observer::class.java) as Observer<CourseEntity>

        viewModel?.getCourse()?.observeForever(observer)

        verify(academyRepository).getCourseWithModules(courseId!!)
    }

    @Test
    fun getModules() {
        val moduleEntities: MutableLiveData<List<ModuleEntity>> = MutableLiveData()
        moduleEntities.value = FakeDataDummy.generateDummyModules(courseId!!)

        `when`(academyRepository.getAllModulesByCourse(courseId!!)).thenReturn(moduleEntities)

        val observer: Observer<List<ModuleEntity>> = mock(Observer::class.java) as Observer<List<ModuleEntity>>

        viewModel?.getModules()?.observeForever(observer)

        verify(academyRepository).getAllModulesByCourse(courseId!!)
    }
}