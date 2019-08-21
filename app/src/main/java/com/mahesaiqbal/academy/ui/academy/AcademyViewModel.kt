package com.mahesaiqbal.academy.ui.academy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mahesaiqbal.academy.data.source.AcademyRepository
import com.mahesaiqbal.academy.data.source.local.entity.CourseEntity
import androidx.lifecycle.Transformations
import com.mahesaiqbal.academy.vo.Resource

class AcademyViewModel(var academyRepository: AcademyRepository) : ViewModel() {

    var login: MutableLiveData<String> = MutableLiveData()

//    var courses: LiveData<Resource<List<CourseEntity>>> = Transformations.switchMap(login, { data -> academyRepository.getAllCourses() })

    var courses = Transformations.switchMap<String, Resource<List<CourseEntity>>>(
        login
    ) { academyRepository.getAllCourses() }

    fun setUsername(username: String) {
        login.value = username
    }
}