package com.mahesaiqbal.academy.ui.detail

import androidx.lifecycle.ViewModel
import com.mahesaiqbal.academy.data.CourseEntity
import com.mahesaiqbal.academy.data.ModuleEntity
import com.mahesaiqbal.academy.utils.DataDummy

class DetailCourseViewModel : ViewModel() {

    lateinit var mCourse: CourseEntity
    var courseId: String? = null
        get() = field
        set(value) { field = value }

    fun getCourse(): CourseEntity {
        for (i in 0 until DataDummy.generateDummyCourses().size) {
            val courseEntity = DataDummy.generateDummyCourses()[i]
            if (courseEntity.courseId == courseId) {
                mCourse = courseEntity
            }
        }
        return mCourse
    }

    fun getModules(): List<ModuleEntity> = DataDummy.generateDummyModules(courseId!!)
}