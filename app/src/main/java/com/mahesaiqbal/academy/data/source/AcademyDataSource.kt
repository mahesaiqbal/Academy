package com.mahesaiqbal.academy.data.source

import com.mahesaiqbal.academy.data.source.local.entity.ModuleEntity
import com.mahesaiqbal.academy.data.source.local.entity.CourseEntity

interface AcademyDataSource {

    fun getAllCourses(): List<CourseEntity>

    fun getCourseWithModules(courseId: String): CourseEntity

    fun getAllModulesByCourse(courseId: String): List<ModuleEntity>

    fun getBookmarkedCourses(): List<CourseEntity>

    fun getContent(courseId: String, moduleId: String): ModuleEntity
}