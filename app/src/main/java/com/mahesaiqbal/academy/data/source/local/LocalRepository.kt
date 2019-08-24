package com.mahesaiqbal.academy.data.source.local

import com.mahesaiqbal.academy.data.source.local.entity.ModuleEntity
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.mahesaiqbal.academy.data.source.local.entity.CourseEntity
import com.mahesaiqbal.academy.data.source.local.entity.CourseWithModule
import com.mahesaiqbal.academy.data.source.local.room.AcademyDao

class LocalRepository(private val mAcademyDao: AcademyDao) {

    companion object {

        private var INSTANCE: LocalRepository? = null

        fun getInstance(academyDao: AcademyDao): LocalRepository {
            if (INSTANCE == null) {
                INSTANCE = LocalRepository(academyDao)
            }
            return INSTANCE!!
        }
    }

    fun getAllCourses(): LiveData<List<CourseEntity>> = mAcademyDao.getCourses()

//    fun getBookmarkedCourses(): LiveData<List<CourseEntity>> = mAcademyDao.getBookmarkedCourse()

    fun getBookmarkedCoursesPaged(): DataSource.Factory<Int, CourseEntity> = mAcademyDao.getBookmarkedCourseAsPaged()

    fun getCourseWithModules(courseId: String): LiveData<CourseWithModule> {
        return mAcademyDao.getCourseWithModuleById(courseId)
    }

    fun getAllModulesByCourse(courseId: String): LiveData<List<ModuleEntity>> {
        return mAcademyDao.getModulesByCourseId(courseId)
    }

    fun insertCourses(courses: List<CourseEntity>) {
        mAcademyDao.insertCourses(courses)
    }

    fun insertModules(modules: List<ModuleEntity>) {
        mAcademyDao.insertModules(modules)
    }

    fun setCourseBookmark(course: CourseEntity, newState: Boolean) {
        course.bookmarked = newState
        mAcademyDao.updateCourse(course)
    }

    fun getModuleWithContent(moduleId: String): LiveData<ModuleEntity> {
        return mAcademyDao.getModuleById(moduleId)
    }

    fun updateContent(content: String, moduleId: String) {
        mAcademyDao.updateModuleByContent(content, moduleId)
    }

    fun setReadModule(module: ModuleEntity) {
        module.read = true
        mAcademyDao.updateModule(module)
    }
}