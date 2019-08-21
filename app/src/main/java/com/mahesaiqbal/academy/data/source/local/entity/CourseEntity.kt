package com.mahesaiqbal.academy.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "courseentities")
data class CourseEntity(
    @PrimaryKey
    @ColumnInfo(name = "courseId")
    val courseId: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "deadline")
    val deadline: String,
    @ColumnInfo(name = "bookmarked")
    var bookmarked: Boolean = false,
    @ColumnInfo(name = "imagePath")
    val imagePath: String
)