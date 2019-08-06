package com.mahesaiqbal.academy.ui.reader

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.mahesaiqbal.academy.data.source.AcademyRepository
import com.mahesaiqbal.academy.data.source.local.entity.ContentEntity
import com.mahesaiqbal.academy.data.source.local.entity.CourseEntity
import com.mahesaiqbal.academy.data.source.local.entity.ModuleEntity
import com.mahesaiqbal.academy.utils.FakeDataDummy
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class CourseReaderViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

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
        val moduleEntities: MutableLiveData<List<ModuleEntity>> = MutableLiveData()
        moduleEntities.value = dummyModules

        `when`(academyRepository.getAllModulesByCourse(courseId)).thenReturn(moduleEntities)

        val observer: Observer<List<ModuleEntity>> = mock(Observer::class.java) as Observer<List<ModuleEntity>>
        viewModel?.getModules()?.observeForever(observer);

        verify(academyRepository).getAllModulesByCourse(courseId);
    }

    @Test
    fun getSelectedModule() {
        val moduleEntity: MutableLiveData<ModuleEntity> = MutableLiveData()

        val dummyModule = dummyModules[0]
        val content =
            "<h3 class=\"fr-text-bordered\">Modul 0 : Introduction</h3><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>"
        dummyModule.contentEntity = ContentEntity(content)

        moduleEntity.setValue(dummyModule)

        `when`(academyRepository.getContent(courseId, moduleId!!)).thenReturn(moduleEntity)

        viewModel?.setSelectedModule(moduleId!!)

        val observer: Observer<ModuleEntity> = mock(Observer::class.java) as Observer<ModuleEntity>
        viewModel?.getSelectedModule()?.observeForever(observer)

        verify(academyRepository).getContent(courseId, moduleId!!)
    }
}