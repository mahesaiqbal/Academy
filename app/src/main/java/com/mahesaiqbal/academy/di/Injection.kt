package com.mahesaiqbal.academy.di

import android.app.Application
import com.mahesaiqbal.academy.data.source.AcademyRepository
import com.mahesaiqbal.academy.data.source.local.LocalRepository
import com.mahesaiqbal.academy.utils.JsonHelper
import com.mahesaiqbal.academy.data.source.remote.RemoteRepository

class Injection {

    companion object {
        fun provideRepository(application: Application): AcademyRepository {
            val localRepository = LocalRepository()
            val remoteRepository = RemoteRepository.getInstance(JsonHelper(application))

            return AcademyRepository.getInstance(localRepository, remoteRepository!!)!!
        }
    }
}