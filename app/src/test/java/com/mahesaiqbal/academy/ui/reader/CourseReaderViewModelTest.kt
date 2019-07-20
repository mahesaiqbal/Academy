package com.mahesaiqbal.academy.ui.reader

import com.mahesaiqbal.academy.data.ContentEntity
import com.mahesaiqbal.academy.data.ModuleEntity
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class CourseReaderViewModelTest {

    var viewModel: CourseReaderViewModel? = null
    var dummyContentEntity: ContentEntity? = null
    var moduleId: String? = null

    @Before
    fun setUp() {
        viewModel = CourseReaderViewModel()
        viewModel!!.setCourseIdValue("a14")

        moduleId = "a14m1"

        val title = viewModel!!.getModules()[0].title
        dummyContentEntity = ContentEntity("<h3 class=\\\"fr-text-bordered\\\">" + title + "</h3><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>")
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getModules() {
        val moduleEntities: List<ModuleEntity> = viewModel!!.getModules()
        assertNotNull(moduleEntities)
        assertEquals(7, moduleEntities.size)
    }

    @Test
    fun getSelectedModule() {
        viewModel!!.setSelectedModule(moduleId!!)
        val moduleEntity = viewModel!!.getSelectedModule()
        assertNotNull(moduleEntity)
        val contentEntity = moduleEntity?.contentEntity
        assertNotNull(contentEntity)
        val content = contentEntity!!.content
        assertNotNull(content)
        assertEquals(content, dummyContentEntity?.content)
    }
}