package com.mahesaiqbal.academy.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mahesaiqbal.academy.data.source.local.LocalRepository
import com.mahesaiqbal.academy.data.source.local.entity.ContentEntity
import com.mahesaiqbal.academy.data.source.local.entity.CourseEntity
import com.mahesaiqbal.academy.data.source.local.entity.ModuleEntity
import com.mahesaiqbal.academy.data.source.remote.RemoteRepository
import com.mahesaiqbal.academy.data.source.remote.response.ContentResponse
import com.mahesaiqbal.academy.data.source.remote.response.CourseResponse
import com.mahesaiqbal.academy.data.source.remote.response.ModuleResponse

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

    override fun getAllCourses(): LiveData<List<CourseEntity>> {
        val courseResults: MutableLiveData<List<CourseEntity>> = MutableLiveData()

        remoteRepository.getAllCourses(object : RemoteRepository.LoadCoursesCallback {
            override fun onAllCoursesReceived(courseResponses: List<CourseResponse>) {
                val courseList: ArrayList<CourseEntity> = arrayListOf()

                for (i in courseResponses.indices) {
                    val response: CourseResponse = courseResponses[i]
                    val (id, title, description, date, imagePath) = response
                    val course = CourseEntity(id, title, description, date, false, imagePath)

                    courseList.add(course)
                }

                courseResults.postValue(courseList)
            }

            override fun onDataNotAvailable() {

            }
        })

        return courseResults
    }


    override fun getCourseWithModules(courseId: String): LiveData<CourseEntity> {
        val courseResult: MutableLiveData<CourseEntity> = MutableLiveData()

        remoteRepository.getAllCourses(object : RemoteRepository.LoadCoursesCallback {
            override fun onAllCoursesReceived(courseResponses: List<CourseResponse>) {
                for (i in courseResponses.indices) {
                    val response: CourseResponse = courseResponses[i]
                    if (response.id.equals(courseId)) {
                        val (id, title, description, date, imagePath) = response
                        val course = CourseEntity(id, title, description, date, false, imagePath)

                        courseResult.postValue(course)
                    }
                }
            }

            override fun onDataNotAvailable() {

            }
        })

        return courseResult
    }


    override fun getBookmarkedCourses(): LiveData<List<CourseEntity>> {
        val courseResults: MutableLiveData<List<CourseEntity>> = MutableLiveData()

        remoteRepository.getAllCourses(object : RemoteRepository.LoadCoursesCallback {
            override fun onAllCoursesReceived(courseResponses: List<CourseResponse>) {
                val courseList: ArrayList<CourseEntity> = arrayListOf()

                for (i in courseResponses.indices) {
                    val response: CourseResponse = courseResponses[i]
                    val (id, title, description, date, imagePath) = response
                    val course = CourseEntity(id, title, description, date, false, imagePath)

                    courseList.add(course)
                }

                courseResults.postValue(courseList)
            }

            override fun onDataNotAvailable() {

            }

        })

        return courseResults
    }

    override fun getAllModulesByCourse(courseId: String): LiveData<List<ModuleEntity>> {
        val moduleResults: MutableLiveData<List<ModuleEntity>> = MutableLiveData()

        remoteRepository.getModules(courseId, object : RemoteRepository.LoadModulesCallback {
            override fun onAllModulesReceived(moduleResponses: List<ModuleResponse>) {
                val moduleList: ArrayList<ModuleEntity> = arrayListOf()

                for (i in moduleResponses.indices) {
                    val response: ModuleResponse = moduleResponses[i]
                    val (moduleId, id, title, position) = response
                    val module = ModuleEntity(moduleId, id, title, position, false)

                    moduleList.add(module)
                }

                moduleResults.postValue(moduleList)
            }

            override fun onDataNotAvailable() {

            }
        })

        return moduleResults
    }


    override fun getContent(courseId: String, moduleId: String): LiveData<ModuleEntity> {
        val moduleResult = MutableLiveData<ModuleEntity>()

        remoteRepository.getModules(courseId, object : RemoteRepository.LoadModulesCallback {
            override fun onAllModulesReceived(moduleResponses: List<ModuleResponse>) {
                val module: ModuleEntity

                for (i in moduleResponses.indices) {
                    val (id, courseId1, title, position) = moduleResponses[i]

                    if (id == moduleId) {
                        module = ModuleEntity(id, courseId1, title, position, false)

                        remoteRepository.getContent(moduleId, object : RemoteRepository.GetContentCallback {
                            override fun onContentReceived(contentResponse: ContentResponse) {
                                module.contentEntity = ContentEntity(contentResponse.content)
                                moduleResult.postValue(module)
                            }

                            override fun onDataNotAvailable() {

                            }
                        })
                        break
                    }
                }
            }

            override fun onDataNotAvailable() {

            }
        })

        return moduleResult
    }

}