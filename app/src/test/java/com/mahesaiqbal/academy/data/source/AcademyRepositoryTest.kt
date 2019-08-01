package com.mahesaiqbal.academy.data.source

import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import com.mahesaiqbal.academy.data.source.remote.RemoteRepository
import org.mockito.Mockito
import com.mahesaiqbal.academy.data.source.local.LocalRepository
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mahesaiqbal.academy.utils.FakeDataDummy
import org.junit.Rule
import org.mockito.Mockito.`when`
import org.junit.Test
import org.mockito.Mockito.verify

class AcademyRepositoryTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val local = Mockito.mock(LocalRepository::class.java)
    private val remote = Mockito.mock(RemoteRepository::class.java)
    private val academyRepository = FakeAcademyRepository(local, remote)

    private val courseResponses = FakeDataDummy.generateRemoteDummyCourses()
    private val courseId = courseResponses[0].id
    private val moduleResponses = FakeDataDummy.generateRemoteDummyModules(courseId)
    private val moduleId = moduleResponses[0].moduleId
    private val content = FakeDataDummy.generateRemoteDummyContent(moduleId)

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getAllCourses() {
        `when`(remote.getAllCourses()).thenReturn(courseResponses)
        val courseEntities = academyRepository.getAllCourses()
        verify(remote).getAllCourses()
        assertNotNull(courseEntities)
        assertEquals(courseResponses.size, courseEntities.size)
    }

    @Test
    fun getAllModulesByCourse() {
        `when`(remote.getModules(courseId)).thenReturn(moduleResponses)
        val moduleEntities = academyRepository.getAllModulesByCourse(courseId)
        verify(remote).getModules(courseId)
        assertNotNull(moduleEntities)
        assertEquals(moduleResponses.size, moduleEntities.size)
    }

    @Test
    fun getBookmarkedCourses() {
        `when`(remote.getAllCourses()).thenReturn(courseResponses)
        val courseEntities = academyRepository.getBookmarkedCourses()
        verify(remote).getAllCourses()
        assertNotNull(courseEntities)
        assertEquals(courseResponses.size, courseEntities.size)
    }

    @Test
    fun getContent() {
        `when`(remote.getModules(courseId)).thenReturn(moduleResponses)
        `when`(remote.getContent(moduleId)).thenReturn(content)
        val resultModule = academyRepository.getContent(courseId, moduleId)
        verify(remote).getContent(moduleId)
        assertNotNull(resultModule)
        assertEquals(content.content, resultModule.contentEntity!!.content)
    }


    @Test
    fun getCourseWithModules() {
        `when`(remote.getAllCourses()).thenReturn(courseResponses)
        val resultCourse = academyRepository.getCourseWithModules(courseId)
        verify(remote).getAllCourses()
        assertNotNull(resultCourse)
        assertEquals(courseResponses[0].title, resultCourse.title)
    }
}