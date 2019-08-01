package com.mahesaiqbal.academy.ui.detail

import androidx.lifecycle.ViewModel
import com.mahesaiqbal.academy.data.source.AcademyRepository
import com.mahesaiqbal.academy.data.source.local.entity.CourseEntity
import com.mahesaiqbal.academy.data.source.local.entity.ModuleEntity
import com.mahesaiqbal.academy.utils.DataDummy

class DetailCourseViewModel(var academyRepository: AcademyRepository) : ViewModel() {

    lateinit var mCourse: CourseEntity
    lateinit var courseId: String

    fun setCourseIdValue(id: String) {
        courseId = id
    }

    fun getCourseIdValue(): String {
        return courseId
    }

    fun getCourse(): CourseEntity = academyRepository.getCourseWithModules(courseId)

    fun getModules(): List<ModuleEntity> = academyRepository.getAllModulesByCourse(courseId)
}