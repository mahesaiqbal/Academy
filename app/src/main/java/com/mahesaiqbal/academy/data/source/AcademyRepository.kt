package com.mahesaiqbal.academy.data.source

import androidx.lifecycle.LiveData
import com.mahesaiqbal.academy.data.source.local.LocalRepository
import com.mahesaiqbal.academy.data.source.local.entity.CourseEntity
import com.mahesaiqbal.academy.data.source.local.entity.ModuleEntity
import com.mahesaiqbal.academy.data.source.remote.RemoteRepository
import com.mahesaiqbal.academy.data.source.remote.response.ContentResponse
import com.mahesaiqbal.academy.data.source.remote.response.CourseResponse
import com.mahesaiqbal.academy.data.source.remote.response.ModuleResponse
import com.mahesaiqbal.academy.utils.AppExecutors
import com.mahesaiqbal.academy.vo.Resource
import com.mahesaiqbal.academy.data.source.local.entity.CourseWithModule
import com.mahesaiqbal.academy.data.source.remote.ApiResponse

class AcademyRepository(
    var localRepository: LocalRepository,
    var remoteRepository: RemoteRepository,
    var appExecutors: AppExecutors
) : AcademyDataSource {

    companion object {
        @Volatile
        private var INSTANCE: AcademyRepository? = null

        fun getInstance(
            localRepository: LocalRepository,
            remoteData: RemoteRepository,
            appExecutor: AppExecutors
        ): AcademyRepository? {
            if (INSTANCE == null) {
                synchronized(AcademyRepository::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = AcademyRepository(localRepository, remoteData, appExecutor)
                    }
                }
            }
            return INSTANCE
        }
    }

    override fun getAllCourses(): LiveData<Resource<List<CourseEntity>>> {
        return object : NetworkBoundResource<List<CourseEntity>, List<CourseResponse>>(appExecutors) {

            public override fun loadFromDB(): LiveData<List<CourseEntity>> {
                return localRepository.getAllCourses()
            }

            override fun shouldFetch(data: List<CourseEntity>): Boolean? {
                return data == null || data.size == 0
            }

            public override fun createCall(): LiveData<ApiResponse<List<CourseResponse>>> {
                return remoteRepository.getAllCoursesAsLiveData()
            }

            public override fun saveCallResult(data: List<CourseResponse>?) {
                val courseEntities = arrayListOf<CourseEntity>()

                for (i in data!!.indices) {
                    val response: CourseResponse = data[i]
                    val (id, title, description, date, imagePath) = response
                    val course = CourseEntity(id, title, description, date, false, imagePath)

                    courseEntities.add(course)
                }

                localRepository.insertCourses(courseEntities)
            }
        }.asLiveData()
    }

    override fun getCourseWithModules(courseId: String): LiveData<Resource<CourseWithModule>> {
        return object : NetworkBoundResource<CourseWithModule, List<ModuleResponse>>(appExecutors) {

            override fun loadFromDB(): LiveData<CourseWithModule> {
                return localRepository.getCourseWithModules(courseId)
            }

            override fun shouldFetch(data: CourseWithModule): Boolean? {
                return data == null || data.modules == null || data.modules!!.size == 0
            }

            override fun createCall(): LiveData<ApiResponse<List<ModuleResponse>>> {
                return remoteRepository.getAllModulesByCourseAsLiveData(courseId)
            }

            override fun saveCallResult(data: List<ModuleResponse>?) {
                val moduleEntities = arrayListOf<ModuleEntity>()

                for (i in data!!.indices) {
                    val response: ModuleResponse = data[i]
                    val (moduleId, courseId, title, position) = response
                    val module = ModuleEntity(moduleId, courseId, title, position, false)

                    moduleEntities.add(module)
                }

                localRepository.insertModules(moduleEntities)
            }
        }.asLiveData()
    }

    override fun getBookmarkedCourses(): LiveData<Resource<List<CourseEntity>>> {
        return object : NetworkBoundResource<List<CourseEntity>, List<CourseResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<CourseEntity>> {
                return localRepository.getBookmarkedCourses()
            }

            override fun shouldFetch(data: List<CourseEntity>): Boolean? {
                return false
            }

            override fun createCall(): LiveData<ApiResponse<List<CourseResponse>>>? {
                return null
            }

            override fun saveCallResult(data: List<CourseResponse>?) {

            }
        }.asLiveData()
    }

    override fun getAllModulesByCourse(courseId: String): LiveData<Resource<List<ModuleEntity>>> {
        return object : NetworkBoundResource<List<ModuleEntity>, List<ModuleResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<ModuleEntity>> {
                return localRepository.getAllModulesByCourse(courseId)
            }

            override fun shouldFetch(data: List<ModuleEntity>): Boolean? {
                return data == null || data.size == 0
            }

            override fun createCall(): LiveData<ApiResponse<List<ModuleResponse>>>? {
                return remoteRepository.getAllModulesByCourseAsLiveData(courseId)
            }

            override fun saveCallResult(data: List<ModuleResponse>?) {

                val moduleEntities = arrayListOf<ModuleEntity>()

                for (i in data!!.indices) {
                    val response: ModuleResponse = data[i]
                    val (moduleId, id, title, position) = response
                    val module = ModuleEntity(moduleId, id, title, position, false)

                    moduleEntities.add(module)
                }

                localRepository.insertModules(moduleEntities)

            }
        }.asLiveData()
    }

    override fun getContent(moduleId: String): LiveData<Resource<ModuleEntity>> {
        return object : NetworkBoundResource<ModuleEntity, ContentResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<ModuleEntity> {
                return localRepository.getModuleWithContent(moduleId)
            }

            override fun shouldFetch(data: ModuleEntity): Boolean? {
                return data.contentEntity == null
            }

            override fun createCall(): LiveData<ApiResponse<ContentResponse>>? {
                return remoteRepository.getContentAsLiveData(moduleId)
            }

            override fun saveCallResult(data: ContentResponse?) {
                localRepository.updateContent(data!!.content, moduleId)
            }
        }.asLiveData()
    }

    override fun setCourseBookmark(course: CourseEntity, state: Boolean) {
        val runnable = { localRepository.setCourseBookmark(course, state) }

        appExecutors.diskIO().execute(runnable)
    }

    override fun setReadModule(module: ModuleEntity) {
        val runnable = { localRepository.setReadModule(module) }

        appExecutors.diskIO().execute(runnable)
    }
}