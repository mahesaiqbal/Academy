package com.mahesaiqbal.academy.data.source

import androidx.lifecycle.LiveData
import com.mahesaiqbal.academy.data.source.local.entity.ModuleEntity
import com.mahesaiqbal.academy.data.source.local.entity.CourseEntity
import com.mahesaiqbal.academy.data.source.local.entity.CourseWithModule
import com.mahesaiqbal.academy.vo.Resource

interface AcademyDataSource {

    fun getAllCourses(): LiveData<Resource<List<CourseEntity>>>

    fun getCourseWithModules(courseId: String): LiveData<Resource<CourseWithModule>>

    fun getAllModulesByCourse(courseId: String): LiveData<Resource<List<ModuleEntity>>>

    fun getBookmarkedCourses(): LiveData<Resource<List<CourseEntity>>>

    fun getContent(moduleId: String): LiveData<Resource<ModuleEntity>>

    fun setCourseBookmark(course: CourseEntity, state: Boolean)

    fun setReadModule(module: ModuleEntity)
}