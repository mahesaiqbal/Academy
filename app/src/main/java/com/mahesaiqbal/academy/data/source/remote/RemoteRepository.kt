package com.mahesaiqbal.academy.data.source.remote

import com.mahesaiqbal.academy.data.source.remote.response.ContentResponse
import com.mahesaiqbal.academy.data.source.remote.response.ModuleResponse
import com.mahesaiqbal.academy.data.source.remote.response.CourseResponse
import android.os.Handler

class RemoteRepository(var jsonHelper: JsonHelper) {

    val SERVICE_LATENCY_IN_MILLIS = 2000L

    companion object {
        private var INSTANCE: RemoteRepository? = null

        fun getInstance(helper: JsonHelper): RemoteRepository? {
            if (INSTANCE == null) {
                INSTANCE = RemoteRepository(helper)
            }
            return INSTANCE
        }
    }

    fun getAllCourses(callback: LoadCoursesCallback) {
        val handler = Handler()
        handler.postDelayed({ callback.onAllCoursesReceived(jsonHelper.loadCourses()) }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getModules(courseId: String, callback: LoadModulesCallback) {
        val handler = Handler()
        handler.postDelayed({ callback.onAllModulesReceived(jsonHelper.loadModule(courseId)) }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getContent(moduleId: String, callback: GetContentCallback) {
        val handler = Handler()
        handler.postDelayed({ callback.onContentReceived(jsonHelper.loadContent(moduleId)!!) }, SERVICE_LATENCY_IN_MILLIS)
    }

    interface LoadCoursesCallback {
        fun onAllCoursesReceived(courseResponses: List<CourseResponse>)
        fun onDataNotAvailable()
    }

    interface LoadModulesCallback {
        fun onAllModulesReceived(moduleResponses: List<ModuleResponse>)
        fun onDataNotAvailable()
    }

    interface GetContentCallback {
        fun onContentReceived(contentResponse: ContentResponse)
        fun onDataNotAvailable()
    }
}