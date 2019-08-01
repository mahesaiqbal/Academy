package com.mahesaiqbal.academy.ui.detail

import com.mahesaiqbal.academy.data.source.AcademyRepository
import com.mahesaiqbal.academy.data.source.local.entity.CourseEntity
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import com.mahesaiqbal.academy.data.source.local.entity.ModuleEntity
import com.mahesaiqbal.academy.utils.FakeDataDummy
import org.mockito.Mockito.*


class DetailCourseViewModelTest {

    var viewModel: DetailCourseViewModel? = null
    var academyRepository: AcademyRepository = mock(AcademyRepository::class.java)
    var dummyCourse: CourseEntity? = FakeDataDummy.generateDummyCourses()[0]
    var courseId = dummyCourse?.courseId

    @Before
    fun setUp() {
        viewModel = DetailCourseViewModel(academyRepository)
        viewModel!!.setCourseIdValue(courseId!!)
        dummyCourse = CourseEntity(
            "a14",
            "Menjadi Android Developer Expert",
            "Dicoding sebagai satu-satunya Google Authorized Training Partner di Indonesia telah melalui proses penyusunan kurikulum secara komprehensif. Semua modul telah diverifikasi langsung oleh Google untuk memastikan bahwa materi yang diajarkan relevan dan sesuai dengan kebutuhan industri digital saat ini. Peserta akan belajar membangun aplikasi Android dengan materi Testing, Debugging, Application, Application UX, Fundamental Application Components, Persistent Data Storage, dan Enhanced System Integration.",
            "100 Hari",
            false,
            "https://www.dicoding.com/images/small/academy/menjadi_android_developer_expert_logo_070119140352.jpg"
        )
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getCourse() {
        `when`(academyRepository.getCourseWithModules(courseId!!)).thenReturn(dummyCourse)
        val courseEntity: CourseEntity = viewModel!!.getCourse()
        verify(academyRepository).getCourseWithModules(courseId!!)
        assertNotNull(courseEntity)
        val courseId = courseEntity.courseId
        assertNotNull(courseId)
        assertEquals(dummyCourse?.courseId, courseId)
    }

    @Test
    fun getModules() {
        `when`(academyRepository.getAllModulesByCourse(courseId!!)).thenReturn(FakeDataDummy.generateDummyModules(courseId!!))
        val moduleEntities: List<ModuleEntity> = viewModel!!.getModules()
        verify(academyRepository).getAllModulesByCourse(courseId!!)
        assertNotNull(moduleEntities)
        assertEquals(7, moduleEntities.size)
    }
}