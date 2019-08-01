package com.mahesaiqbal.academy.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.mahesaiqbal.academy.data.source.AcademyRepository
import com.mahesaiqbal.academy.di.Injection
import androidx.lifecycle.ViewModel
import com.mahesaiqbal.academy.ui.reader.CourseReaderViewModel
import com.mahesaiqbal.academy.ui.bookmark.BookmarkViewModel
import com.mahesaiqbal.academy.ui.detail.DetailCourseViewModel
import com.mahesaiqbal.academy.ui.academy.AcademyViewModel

class ViewModelFactory(academyRepository: AcademyRepository) : NewInstanceFactory() {

    var mAcademyRepository: AcademyRepository = academyRepository

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application): ViewModelFactory? {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = ViewModelFactory(Injection.provideRepository(application))
                    }
                }
            }
            return INSTANCE
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AcademyViewModel::class.java)) {
            return AcademyViewModel(mAcademyRepository) as T
        } else if (modelClass.isAssignableFrom(DetailCourseViewModel::class.java)) {
            return DetailCourseViewModel(mAcademyRepository) as T
        } else if (modelClass.isAssignableFrom(BookmarkViewModel::class.java)) {
            return BookmarkViewModel(mAcademyRepository) as T
        } else if (modelClass.isAssignableFrom(CourseReaderViewModel::class.java)) {
            return CourseReaderViewModel(mAcademyRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}