package com.mahesaiqbal.academy.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mahesaiqbal.academy.data.source.AcademyRepository
import com.mahesaiqbal.academy.data.source.local.entity.CourseEntity
import com.mahesaiqbal.academy.data.source.local.entity.ModuleEntity
import com.mahesaiqbal.academy.utils.DataDummy

class DetailCourseViewModel(var academyRepository: AcademyRepository) : ViewModel() {

    lateinit var courseId: String

    fun setCourseIdValue(id: String) {
        courseId = id
    }

    fun getCourseIdValue(): String {
        return courseId
    }

    fun getCourse(): LiveData<CourseEntity> = academyRepository.getCourseWithModules(courseId)

    fun getModules(): LiveData<List<ModuleEntity>> = academyRepository.getAllModulesByCourse(courseId)
}