package com.mahesaiqbal.academy.ui.bookmark

import androidx.lifecycle.ViewModel
import com.mahesaiqbal.academy.data.CourseEntity
import com.mahesaiqbal.academy.utils.DataDummy

class BookmarkViewModel : ViewModel() {

    fun getBookmarks(): List<CourseEntity> = DataDummy.generateDummyCourses()
}