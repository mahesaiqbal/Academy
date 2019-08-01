package com.mahesaiqbal.academy.data.source

import com.mahesaiqbal.academy.data.source.local.LocalRepository
import com.mahesaiqbal.academy.data.source.local.entity.ContentEntity
import com.mahesaiqbal.academy.data.source.local.entity.CourseEntity
import com.mahesaiqbal.academy.data.source.local.entity.ModuleEntity
import com.mahesaiqbal.academy.data.source.remote.RemoteRepository

class FakeAcademyRepository(var localRepository: LocalRepository, var remoteRepository: RemoteRepository) : AcademyDataSource {

    companion object {
        @Volatile
        var INSTANCE: FakeAcademyRepository? = null

        fun getInstance(localRepository: LocalRepository, remoteData: RemoteRepository): FakeAcademyRepository? {
            if (INSTANCE == null) {
                synchronized(FakeAcademyRepository::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = FakeAcademyRepository(localRepository, remoteData)
                    }
                }
            }
            return INSTANCE
        }
    }

    override fun getAllCourses(): ArrayList<CourseEntity> {
        val courseResponses = remoteRepository.getAllCourses()
        val courseList: ArrayList<CourseEntity> = arrayListOf()
        for (i in courseResponses.indices) {
            val (id, title, description, date, imagePath) = courseResponses[i]
            val course = CourseEntity(id, title, description, date, false, imagePath)

            courseList.add(course)
        }
        return courseList
    }


    override fun getCourseWithModules(courseId: String): CourseEntity {
        var course: CourseEntity? = null
        val courses = remoteRepository.getAllCourses()
        for (i in courses.indices) {
            val (id, title, description, date, imagePath) = courses[i]
            if (id == courseId) {
                course = CourseEntity(
                    id,
                    title,
                    description,
                    date,
                    false,
                    imagePath
                )
            }
        }
        return course!!
    }


    override fun getBookmarkedCourses(): ArrayList<CourseEntity> {
        val courseList: ArrayList<CourseEntity> = arrayListOf()
        val courses = remoteRepository.getAllCourses()
        for (i in courses.indices) {
            val (id, title, description, date, imagePath) = courses[i]
            val course = CourseEntity(
                id,
                title,
                description,
                date,
                false,
                imagePath
            )
            courseList.add(course)
        }
        return courseList
    }

    override fun getAllModulesByCourse(courseId: String): ArrayList<ModuleEntity> {
        val moduleList: ArrayList<ModuleEntity> = arrayListOf()
        val moduleResponses = remoteRepository.getModules(courseId)
        for (i in moduleResponses.indices) {
            val (moduleId, courseId1, title, position) = moduleResponses[i]
            val course = ModuleEntity(
                moduleId,
                courseId1,
                title,
                position,
                false
            )

            moduleList.add(course)
        }

        return moduleList
    }


    override fun getContent(courseId: String, moduleId: String): ModuleEntity {
        val moduleResponses = remoteRepository.getModules(courseId)

        var module: ModuleEntity? = null
        for (i in moduleResponses.indices) {
            val (id, courseId1, title, position) = moduleResponses[i]

            if (id == moduleId) {
                module = ModuleEntity(id, courseId1, title, position, false)

                module.contentEntity = ContentEntity(remoteRepository.getContent(moduleId)!!.content)
                break
            }
        }

        return module!!
    }

}