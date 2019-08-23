package com.mahesaiqbal.academy.data.source;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import com.mahesaiqbal.academy.data.source.local.LocalRepository;
import com.mahesaiqbal.academy.data.source.local.entity.CourseEntity;
import com.mahesaiqbal.academy.data.source.local.entity.ModuleEntity;
import com.mahesaiqbal.academy.data.source.remote.RemoteRepository;
import com.mahesaiqbal.academy.data.source.remote.response.ContentResponse;
import com.mahesaiqbal.academy.data.source.remote.response.CourseResponse;
import com.mahesaiqbal.academy.data.source.remote.response.ModuleResponse;
import com.mahesaiqbal.academy.utils.FakeDataDummy;

import com.mahesaiqbal.academy.utils.LiveDataTest;
import com.mahesaiqbal.academy.utils.LiveDataTestUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class AcademyRepositoryTesting {

//    @Rule
//    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
//
//    private LocalRepository local = Mockito.mock(LocalRepository.class);
//    private RemoteRepository remote = Mockito.mock(RemoteRepository.class);
//    private FakeAcademyRepository academyRepository = new FakeAcademyRepository(local, remote);
//
//    private ArrayList<CourseResponse> courseResponses = FakeDataDummy.Companion.generateRemoteDummyCourses();
//    private String courseId = courseResponses.get(0).getId();
//    private ArrayList<ModuleResponse> moduleResponses = FakeDataDummy.Companion.generateRemoteDummyModules(courseId);
//    private String moduleId = moduleResponses.get(0).getModuleId();
//    private ContentResponse content = FakeDataDummy.Companion.generateRemoteDummyContent(moduleId);
//
//    @Test
//    public void getAllCourses() {
//
//        doAnswer(invocation -> {
//            ((RemoteRepository.LoadCoursesCallback) invocation.getArguments()[0])
//                    .onAllCoursesReceived(courseResponses);
//            return null;
//        }).when(remote).getAllCourses(any(RemoteRepository.LoadCoursesCallback.class));
//
//        List<CourseEntity> result = LiveDataTest.getValue(academyRepository.getAllCourses());
//
//        verify(remote, times(1)).getAllCourses(any(RemoteRepository.LoadCoursesCallback.class));
//
//        assertEquals(courseResponses.size(), result.size());
//    }
//
//    @Test
//    public void getAllModulesByCourse() {
//        doAnswer(invocation -> {
//            ((RemoteRepository.LoadModulesCallback) invocation.getArguments()[1])
//                    .onAllModulesReceived(moduleResponses);
//            return null;
//        }).when(remote).getModules(eq(courseId), any(RemoteRepository.LoadModulesCallback.class));
//
//        List<ModuleEntity> result = LiveDataTest.getValue(academyRepository.getAllModulesByCourse(courseId));
//
//        verify(remote, times(1)).getModules(eq(courseId), any(RemoteRepository.LoadModulesCallback.class));
//
//        assertEquals(moduleResponses.size(), result.size());
//    }
//
//    @Test
//    public void getBookmarkedCourses() {
//        doAnswer(invocation -> {
//            ((RemoteRepository.LoadCoursesCallback) invocation.getArguments()[0])
//                    .onAllCoursesReceived(courseResponses);
//            return null;
//        }).when(remote).getAllCourses(any(RemoteRepository.LoadCoursesCallback.class));
//
//        List<CourseEntity> result = LiveDataTest.getValue(academyRepository.getBookmarkedCourses());
//
//        verify(remote, times(1)).getAllCourses(any(RemoteRepository.LoadCoursesCallback.class));
//
//        assertEquals(courseResponses.size(), result.size());
//    }
//
//    @Test
//    public void getContent() {
//        doAnswer(invocation -> {
//            ((RemoteRepository.LoadModulesCallback) invocation.getArguments()[1])
//                    .onAllModulesReceived(moduleResponses);
//            return null;
//        }).when(remote).getModules(eq(courseId), any(RemoteRepository.LoadModulesCallback.class));
//
//        doAnswer(invocation -> {
//            ((RemoteRepository.GetContentCallback) invocation.getArguments()[1])
//                    .onContentReceived(content);
//            return null;
//        }).when(remote).getContent(eq(moduleId), any(RemoteRepository.GetContentCallback.class));
//
//        ModuleEntity resultContent = LiveDataTest.getValue(academyRepository.getContent(courseId, moduleId));
//
//        verify(remote, times(1))
//                .getModules(eq(courseId), any(RemoteRepository.LoadModulesCallback.class));
//
//        verify(remote, times(1))
//                .getContent(eq(moduleId), any(RemoteRepository.GetContentCallback.class));
//
//        assertEquals(content.getContent(), resultContent.getContentEntity().getContent());
//    }
//
//
//    @Test
//    public void getCourseWithModules() {
//        doAnswer(invocation -> {
//            ((RemoteRepository.LoadCoursesCallback) invocation.getArguments()[0])
//                    .onAllCoursesReceived(courseResponses);
//            return null;
//        }).when(remote).getAllCourses(any(RemoteRepository.LoadCoursesCallback.class));
//
//        CourseEntity result = LiveDataTest.getValue(academyRepository.getCourseWithModules(courseId));
//
//        verify(remote, times(1)).getAllCourses(any(RemoteRepository.LoadCoursesCallback.class));
//
//        assertEquals(courseResponses.get(0).getTitle(), result.getTitle());
//    }

}
