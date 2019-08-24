package com.mahesaiqbal.academy.ui.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.mahesaiqbal.academy.data.source.AcademyRepository
import com.mahesaiqbal.academy.data.source.local.entity.CourseEntity
import com.mahesaiqbal.academy.vo.Resource

class BookmarkViewModel(var academyRepository: AcademyRepository) : ViewModel() {

    fun getBookmarks(): LiveData<Resource<PagedList<CourseEntity>>> = academyRepository.getBookmarkedCoursesPaged()

    fun setBookmark(courseEntity: CourseEntity) {
        val newState = !courseEntity.bookmarked
        academyRepository.setCourseBookmark(courseEntity, newState)
    }
}