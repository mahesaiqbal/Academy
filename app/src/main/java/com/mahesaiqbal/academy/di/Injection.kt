package com.mahesaiqbal.academy.di

import android.app.Application
import com.mahesaiqbal.academy.data.source.AcademyRepository
import com.mahesaiqbal.academy.data.source.local.LocalRepository
import com.mahesaiqbal.academy.data.source.local.room.AcademyDatabase
import com.mahesaiqbal.academy.utils.JsonHelper
import com.mahesaiqbal.academy.data.source.remote.RemoteRepository
import com.mahesaiqbal.academy.utils.AppExecutors

class Injection {

    companion object {
        fun provideRepository(application: Application): AcademyRepository {
            val database = AcademyDatabase.getInstance(application)

            val localRepository = LocalRepository.getInstance(database.academyDao())
            val remoteRepository = RemoteRepository.getInstance(JsonHelper(application))

            val appExecutors = AppExecutors()

            return AcademyRepository.getInstance(localRepository, remoteRepository!!, appExecutors)!!
        }
    }
}