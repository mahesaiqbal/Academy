package com.mahesaiqbal.academy.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.mahesaiqbal.academy.data.source.local.LocalRepository
import com.mahesaiqbal.academy.data.source.local.entity.CourseEntity
import com.mahesaiqbal.academy.data.source.local.entity.CourseWithModule
import com.mahesaiqbal.academy.data.source.local.entity.ModuleEntity
import com.mahesaiqbal.academy.data.source.remote.RemoteRepository
import com.mahesaiqbal.academy.data.source.remote.response.CourseResponse
import com.mahesaiqbal.academy.data.source.remote.response.ModuleResponse
import com.mahesaiqbal.academy.utils.FakeDataDummy
import com.mahesaiqbal.academy.utils.InstantAppExecutors
import com.mahesaiqbal.academy.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.util.*

class AcademyRepositoryTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    val local = mock(LocalRepository::class.java)
    val remote = mock(RemoteRepository::class.java)
    val instantAppExecutors = mock(InstantAppExecutors::class.java)
    val academyRepository = FakeAcademyRepository(local, remote, instantAppExecutors)

    val courseResponses: ArrayList<CourseResponse> = FakeDataDummy.generateRemoteDummyCourses()
    val courseId = courseResponses[0].id
    val moduleResponses: ArrayList<ModuleResponse> = FakeDataDummy.generateRemoteDummyModules(courseId)
    val moduleId = moduleResponses[0].moduleId
    val content = FakeDataDummy.generateRemoteDummyContent(moduleId)

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getAllCourses() {
        val dummyCourses = MutableLiveData<List<CourseEntity>>()
        dummyCourses.setValue(FakeDataDummy.generateDummyCourses())

        `when`(local.getAllCourses()).thenReturn(dummyCourses)

        val result = LiveDataTestUtil.getValue(academyRepository.getAllCourses())

        verify(local).getAllCourses()
        assertNotNull(result.data)
        assertEquals(courseResponses.size, result.data?.size)
    }

    @Test
    fun getAllModulesByCourse() {
        val dummyModules = MutableLiveData<List<ModuleEntity>>()
        dummyModules.setValue(FakeDataDummy.generateDummyModules(courseId))

        `when`(local.getAllModulesByCourse(courseId)).thenReturn(
            dummyModules
        )

        val result = LiveDataTestUtil.getValue(academyRepository.getAllModulesByCourse(courseId))

        verify(local).getAllModulesByCourse(courseId)
        assertNotNull(result.data)
        assertEquals(moduleResponses.size, result.data?.size)
    }

    @Test
    fun getBookmarkedCourses() {
        val dummyCourses = MutableLiveData<List<CourseEntity>>()
        dummyCourses.setValue(FakeDataDummy.generateDummyCourses())

        `when`(local.getBookmarkedCourses()).thenReturn(dummyCourses)

        val result = LiveDataTestUtil.getValue(academyRepository.getBookmarkedCourses())

        verify(local).getBookmarkedCourses()
        assertNotNull(result.data)
        assertEquals(courseResponses.size, result.data?.size)
    }

    @Test
    fun getContent() {
        val dummyEntity = MutableLiveData<ModuleEntity>()
        dummyEntity.setValue(FakeDataDummy.generateDummyModuleWithContent(moduleId))

        `when`(local.getModuleWithContent(courseId)).thenReturn(dummyEntity)

        val result = LiveDataTestUtil.getValue(academyRepository.getContent(courseId))

        verify(local).getModuleWithContent(courseId)
        assertNotNull(result)

        assertNotNull(result.data)
        assertNotNull(result.data?.contentEntity)
        assertNotNull(result.data?.contentEntity?.content)
        assertEquals(content.content, result.data?.contentEntity?.content)
    }


    @Test
    fun getCourseWithModules() {
        val dummyEntity = MutableLiveData<CourseWithModule>()
        dummyEntity.setValue(
            FakeDataDummy.generateDummyCourseWithModules(
                FakeDataDummy.generateDummyCourses()[0],
                false
            )
        )

        `when`(local.getCourseWithModules(courseId)).thenReturn(dummyEntity)

        val result = LiveDataTestUtil.getValue(academyRepository.getCourseWithModules(courseId))

        verify(local).getCourseWithModules(courseId)
        assertNotNull(result.data)
        assertNotNull(result.data?.course?.title)
        assertEquals(courseResponses[0].title, result.data?.course?.title)
    }
}