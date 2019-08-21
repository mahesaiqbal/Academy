package com.mahesaiqbal.academy.data.source.remote

import com.mahesaiqbal.academy.data.source.remote.response.ContentResponse
import com.mahesaiqbal.academy.data.source.remote.response.ModuleResponse
import com.mahesaiqbal.academy.data.source.remote.response.CourseResponse
import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    fun getAllCoursesAsLiveData(): LiveData<ApiResponse<List<CourseResponse>>> {
        EspressoIdlingResource.increment()
        val resultCourse: MutableLiveData<ApiResponse<List<CourseResponse>>> = MutableLiveData()

        Handler().postDelayed({
            resultCourse.value = ApiResponse.success(jsonHelper.loadCourses())
            if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                EspressoIdlingResource.decrement()
            }
        }, SERVICE_LATENCY_IN_MILLIS)

        return resultCourse
    }

    fun getAllModulesByCourseAsLiveData(courseId: String): LiveData<ApiResponse<List<ModuleResponse>>> {
        EspressoIdlingResource.increment()
        val resultModules: MutableLiveData<ApiResponse<List<ModuleResponse>>> = MutableLiveData()

        Handler().postDelayed({
            resultModules.value = ApiResponse.success(jsonHelper.loadModule(courseId))
            if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                EspressoIdlingResource.decrement()
            }
        }, SERVICE_LATENCY_IN_MILLIS)

        return resultModules
    }

    fun getContentAsLiveData(moduleId: String): LiveData<ApiResponse<ContentResponse>> {
        EspressoIdlingResource.increment()
        val resultContent: MutableLiveData<ApiResponse<ContentResponse>> = MutableLiveData()

        Handler().postDelayed({
            resultContent.value = ApiResponse.success(jsonHelper.loadContent(moduleId))
            if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                EspressoIdlingResource.decrement()
            }
        }, SERVICE_LATENCY_IN_MILLIS)

        return resultContent
    }
}