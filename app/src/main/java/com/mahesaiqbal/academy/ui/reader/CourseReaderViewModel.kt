package com.mahesaiqbal.academy.ui.reader

import androidx.lifecycle.ViewModel
import com.mahesaiqbal.academy.data.source.AcademyRepository
import com.mahesaiqbal.academy.data.source.local.entity.ContentEntity
import com.mahesaiqbal.academy.data.source.local.entity.ModuleEntity
import com.mahesaiqbal.academy.utils.DataDummy

class CourseReaderViewModel(var academyRepository: AcademyRepository) : ViewModel() {

    lateinit var courseId: String
    lateinit var moduleId: String

    fun setCourseIdValue(id: String) {
        courseId = id
    }

    fun setSelectedModule(id: String) {
        moduleId = id
    }

    fun getSelectedModule(): ModuleEntity? = academyRepository.getContent(courseId, moduleId)

    fun getModules(): ArrayList<ModuleEntity> = academyRepository.getAllModulesByCourse(courseId)
}