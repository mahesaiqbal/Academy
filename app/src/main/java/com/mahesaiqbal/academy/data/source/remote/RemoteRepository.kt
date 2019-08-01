package com.mahesaiqbal.academy.data.source.remote

import com.mahesaiqbal.academy.data.source.remote.response.ContentResponse
import com.mahesaiqbal.academy.data.source.remote.response.ModuleResponse
import com.mahesaiqbal.academy.data.source.remote.response.CourseResponse

class RemoteRepository(var jsonHelper: JsonHelper) {

    companion object {
        private var INSTANCE: RemoteRepository? = null

        fun getInstance(helper: JsonHelper): RemoteRepository? {
            if (INSTANCE == null) {
                INSTANCE = RemoteRepository(helper)
            }
            return INSTANCE
        }
    }

    fun getAllCourses(): List<CourseResponse> {
        return jsonHelper.loadCourses()
    }

    fun getModules(courseId: String): List<ModuleResponse> {
        return jsonHelper.loadModule(courseId)
    }

    fun getContent(moduleId: String): ContentResponse? {
        return jsonHelper.loadContent(moduleId)
    }
}