package com.mahesaiqbal.academy.ui.reader

import com.mahesaiqbal.academy.data.source.AcademyRepository
import com.mahesaiqbal.academy.data.source.local.entity.ContentEntity
import com.mahesaiqbal.academy.data.source.local.entity.CourseEntity
import com.mahesaiqbal.academy.data.source.local.entity.ModuleEntity
import com.mahesaiqbal.academy.utils.FakeDataDummy
import org.junit.After
import org.junit.Before
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.Mockito.`when`

class CourseReaderViewModelTest {

    var viewModel: CourseReaderViewModel? = null
    var academyRepository: AcademyRepository = mock(AcademyRepository::class.java)
    var dummyCourse: CourseEntity = FakeDataDummy.generateDummyCourses()[0]
    var courseId = dummyCourse.courseId
    var dummyModules: ArrayList<ModuleEntity> = FakeDataDummy.generateDummyModules(courseId)
    var moduleId: String? = dummyModules[0].moduleId

    @Before
    fun setUp() {
        viewModel = CourseReaderViewModel(academyRepository)
        viewModel!!.setCourseIdValue(courseId)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getModules() {
        `when`(academyRepository.getAllModulesByCourse(courseId)).thenReturn(dummyModules)
        val moduleEntities: List<ModuleEntity> = viewModel!!.getModules()
        verify(academyRepository).getAllModulesByCourse(courseId)
        assertNotNull(moduleEntities)
        assertEquals(7, moduleEntities.size)
    }

    @Test
    fun getSelectedModule() {
        val moduleEntity = dummyModules[0]
        val content = "<h3 class=\"fr-text-bordered\">Modul 0 : Introduction</h3><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>"
        moduleEntity.contentEntity = ContentEntity(content)
        viewModel?.setSelectedModule(moduleId!!)
        `when`(academyRepository.getContent(courseId, moduleId!!)).thenReturn(moduleEntity)
        val entity = viewModel?.getSelectedModule()
        verify(academyRepository).getContent(courseId, moduleId!!)
        assertNotNull(entity)

        val contentEntity = entity?.contentEntity
        assertNotNull(contentEntity)

        val resultContent = contentEntity!!.content
        assertNotNull(resultContent)

        assertEquals(content, resultContent)
    }
}