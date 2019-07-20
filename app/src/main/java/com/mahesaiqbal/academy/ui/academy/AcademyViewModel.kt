package com.mahesaiqbal.academy.ui.academy

import androidx.lifecycle.ViewModel
import com.mahesaiqbal.academy.data.CourseEntity
import com.mahesaiqbal.academy.utils.DataDummy

class AcademyViewModel : ViewModel() {

    fun getCourses(): List<CourseEntity> = DataDummy.generateDummyCourses()
}