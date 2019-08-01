package com.mahesaiqbal.academy.data.source.remote

import android.app.Application
import java.io.IOException
import java.io.InputStream
import org.json.JSONException
import com.mahesaiqbal.academy.data.source.remote.response.ContentResponse
import org.json.JSONObject
import com.mahesaiqbal.academy.data.source.remote.response.ModuleResponse
import com.mahesaiqbal.academy.data.source.remote.response.CourseResponse

class JsonHelper(var application: Application) {

    fun parsingFileToString(fileName: String): String? {
        try {
            val inputStream: InputStream = application.assets.open(fileName)
            val buffer = ByteArray(inputStream.available())
            inputStream.read(buffer)
            inputStream.close()
            return String(buffer)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
    }

    fun loadCourses(): List<CourseResponse> {
        val list: ArrayList<CourseResponse> = arrayListOf()

        try {
            val responseObject = JSONObject(parsingFileToString("CourseResponses.json"))
            val listArray = responseObject.getJSONArray("courses")
            for (i in 0 until listArray.length()) {
                val course = listArray.getJSONObject(i)

                val id = course.getString("id")
                val title = course.getString("title")
                val description = course.getString("description")
                val date = course.getString("date")
                val imagePath = course.getString("imagePath")

                val courseResponse = CourseResponse(id, title, description, date, imagePath)
                list.add(courseResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }

    fun loadModule(courseId: String): List<ModuleResponse> {
        val fileName = String.format("Module_%s.json", courseId)
        val list: ArrayList<ModuleResponse> = arrayListOf()

        try {

            val result = parsingFileToString(fileName)
            if (result != null) {
                val responseObject = JSONObject(result)
                val listArray = responseObject.getJSONArray("modules")
                for (i in 0 until listArray.length()) {
                    val course = listArray.getJSONObject(i)

                    val moduleId = course.getString("moduleId")
                    val title = course.getString("title")
                    val position = course.getString("position")

                    val courseResponse = ModuleResponse(moduleId, courseId, title, Integer.parseInt(position))
                    list.add(courseResponse)
                }
            }

        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }

    fun loadContent(moduleId: String): ContentResponse? {
        val fileName = String.format("Content_%s.json", moduleId)
        var contentResponse: ContentResponse? = null
        try {

            val result = parsingFileToString(fileName)
            if (result != null) {
                val responseObject = JSONObject(result)

                val content = responseObject.getString("content")

                contentResponse = ContentResponse(moduleId, content)
            }

        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return contentResponse
    }
}