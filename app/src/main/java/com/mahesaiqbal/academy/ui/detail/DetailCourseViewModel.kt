package com.mahesaiqbal.academy.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.mahesaiqbal.academy.data.source.AcademyRepository
import com.mahesaiqbal.academy.data.source.local.entity.CourseEntity
import com.mahesaiqbal.academy.data.source.local.entity.CourseWithModule
import com.mahesaiqbal.academy.data.source.local.entity.ModuleEntity
import com.mahesaiqbal.academy.vo.Resource

class DetailCourseViewModel(var academyRepository: AcademyRepository) : ViewModel() {

    var courseId: MutableLiveData<String> = MutableLiveData()

    var courseModule: LiveData<Resource<CourseWithModule>> = Transformations.switchMap(courseId) {
            courseId -> academyRepository.getCourseWithModules(courseId)
    }

    fun setCourseIdValue(id: String) {
        courseId.value = id
    }

    fun getCourseIdValue(): String = courseId.value!!

    fun setBookmark() {
        if (courseModule.value != null) {
            val courseWithModule: CourseWithModule? = courseModule.value!!.data

            if (courseWithModule != null) {
                val courseEntity: CourseEntity? = courseWithModule.course
                val newState = !courseEntity!!.bookmarked

                academyRepository.setCourseBookmark(courseEntity, newState)
            }
        }
    }
}