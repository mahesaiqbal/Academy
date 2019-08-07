package com.mahesaiqbal.academy.data.source.remote

import com.mahesaiqbal.academy.data.source.remote.response.ContentResponse
import com.mahesaiqbal.academy.data.source.remote.response.ModuleResponse
import com.mahesaiqbal.academy.data.source.remote.response.CourseResponse
import android.os.Handler
import com.mahesaiqbal.academy.utils.EspressoIdlingResource
import com.mahesaiqbal.academy.utils.JsonHelper

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
        EspressoIdlingResource.increment()

        Handler().postDelayed({
            callback.onAllCoursesReceived(jsonHelper.loadCourses())
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getModules(courseId: String, callback: LoadModulesCallback) {
        EspressoIdlingResource.increment()

        Handler().postDelayed({
            callback.onAllModulesReceived(jsonHelper.loadModule(courseId))
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getContent(moduleId: String, callback: GetContentCallback) {
        EspressoIdlingResource.increment()

        Handler().postDelayed({
            callback.onContentReceived(jsonHelper.loadContent(moduleId)!!)
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
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