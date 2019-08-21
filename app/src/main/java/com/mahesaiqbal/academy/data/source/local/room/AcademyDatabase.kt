package com.mahesaiqbal.academy.data.source.local.room

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mahesaiqbal.academy.data.source.local.entity.ModuleEntity
import com.mahesaiqbal.academy.data.source.local.entity.CourseEntity
import androidx.room.Database

@Database(entities = arrayOf(CourseEntity::class, ModuleEntity::class), version = 1, exportSchema = false)
abstract class AcademyDatabase : RoomDatabase() {

    abstract fun academyDao(): AcademyDao

    companion object {

        private var INSTANCE: AcademyDatabase? = null

        private val sLock = Any()

        fun getInstance(context: Context): AcademyDatabase {
            synchronized(sLock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AcademyDatabase::class.java, "Academies.db"
                    )
                        .build()
                }
                return INSTANCE!!
            }
        }
    }

}