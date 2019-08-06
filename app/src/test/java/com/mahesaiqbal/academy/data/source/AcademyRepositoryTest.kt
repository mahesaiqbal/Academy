package com.mahesaiqbal.academy.data.source

//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//
//import com.mahesaiqbal.academy.data.source.local.LocalRepository
//import com.mahesaiqbal.academy.data.source.remote.RemoteRepository
//import com.mahesaiqbal.academy.data.source.remote.response.CourseResponse
//import com.mahesaiqbal.academy.data.source.remote.response.ModuleResponse
//import com.mahesaiqbal.academy.utils.FakeDataDummy
//import com.mahesaiqbal.academy.utils.LiveDataTestUtil
//import com.nhaarman.mockitokotlin2.doReturn
//import com.nhaarman.mockitokotlin2.mock
//import org.junit.After
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//
//import java.util.ArrayList
//
//import org.junit.Assert.assertEquals
////import org.mockito.ArgumentMatchers.any
////import org.mockito.ArgumentMatchers.eq
////import org.mockito.Mockito.*
//
//class AcademyRepositoryTest {
//
//    @Rule
//    @JvmField
//    var instantTaskExecutorRule = InstantTaskExecutorRule()
//
////    val local = mock(LocalRepository::class.java)
////    val remote = mock(RemoteRepository::class.java)
////    val academyRepository = FakeAcademyRepository(local, remote)
////
////    val courseResponses: ArrayList<CourseResponse> = FakeDataDummy.generateRemoteDummyCourses()
////    val courseId = courseResponses[0].id
////    val moduleResponses: ArrayList<ModuleResponse> = FakeDataDummy.generateRemoteDummyModules(courseId)
////    val moduleId = moduleResponses[0].moduleId
////    val content = FakeDataDummy.generateRemoteDummyContent(moduleId)
//
//    @Before
//    fun setUp() {
//    }
//
//    @After
//    fun tearDown() {
//    }
//
//    @Test
//    fun getAllCourses() {
////        doAnswer { invocation ->
////            (invocation.arguments[0] as RemoteRepository.LoadCoursesCallback)
////                .onAllCoursesReceived(courseResponses)
////            null
////        }.`when`(remote).getAllCourses(any(RemoteRepository.LoadCoursesCallback::class.java))
////
////        val result = LiveDataTestUtil.getValue(academyRepository.getAllCourses())
////
////        verify(remote, times(1)).getAllCourses(any(RemoteRepository.LoadCoursesCallback::class.java))
////
////        assertEquals(courseResponses.size, result.size)
//        val mock = mock<RemoteRepository> {
//            on { getAllCourses() } doReturn
//        }
//    }
//
////    @Test
////    fun getAllModulesByCourse() {
////        doAnswer { invocation ->
////            (invocation.arguments[1] as RemoteRepository.LoadModulesCallback)
////                .onAllModulesReceived(moduleResponses)
////            null
////        }.`when`(remote).getModules(eq(courseId), any(RemoteRepository.LoadModulesCallback::class.java))
////
////        val result = LiveDataTestUtil.getValue(academyRepository.getAllModulesByCourse(courseId))
////
////        verify(remote, times(1)).getModules(eq(courseId), any(RemoteRepository.LoadModulesCallback::class.java))
////
////        assertEquals(moduleResponses.size, result.size)
////    }
//
////    @Test
////    fun getBookmarkedCourses() {
////        doAnswer { invocation ->
////            (invocation.arguments[0] as RemoteRepository.LoadCoursesCallback)
////                .onAllCoursesReceived(courseResponses)
////            null
////        }.`when`(remote).getAllCourses(any(RemoteRepository.LoadCoursesCallback::class.java))
////
////        val result = LiveDataTestUtil.getValue(academyRepository.getBookmarkedCourses())
////
////        verify(remote, times(1)).getAllCourses(any(RemoteRepository.LoadCoursesCallback::class.java))
////
////        assertEquals(courseResponses.size, result.size)
////    }
////
////    @Test
////    fun getContent() {
////        doAnswer { invocation ->
////            (invocation.arguments[1] as RemoteRepository.LoadModulesCallback)
////                .onAllModulesReceived(moduleResponses)
////            null
////        }.`when`(remote).getModules(eq(courseId), any(RemoteRepository.LoadModulesCallback::class.java))
////
////        doAnswer { invocation ->
////            (invocation.arguments[1] as RemoteRepository.GetContentCallback)
////                .onContentReceived(content)
////            null
////        }.`when`(remote).getContent(eq(moduleId), any(RemoteRepository.GetContentCallback::class.java))
////
////        val resultContent = LiveDataTestUtil.getValue(academyRepository.getContent(courseId, moduleId))
////
////        verify(remote, times(1))
////            .getModules(eq(courseId), any(RemoteRepository.LoadModulesCallback::class.java))
////
////        verify(remote, times(1))
////            .getContent(eq(moduleId), any(RemoteRepository.GetContentCallback::class.java))
////
////        assertEquals(content.content, resultContent.contentEntity!!.content)
////    }
////
////
////    @Test
////    fun getCourseWithModules() {
////        doAnswer { invocation ->
////            (invocation.arguments[0] as RemoteRepository.LoadCoursesCallback)
////                .onAllCoursesReceived(courseResponses)
////            null
////        }.`when`(remote).getAllCourses(any(RemoteRepository.LoadCoursesCallback::class.java))
////
////        val (_, title) = LiveDataTestUtil.getValue(academyRepository.getCourseWithModules(courseId))
////
////        verify(remote, times(1)).getAllCourses(any(RemoteRepository.LoadCoursesCallback::class.java))
////
////        assertEquals(courseResponses[0].title, title)
////    }
//}