package com.mahesaiqbal.academy.data.source.local.entity

import androidx.room.*

@Entity(
    tableName = "moduleentities", primaryKeys = arrayOf("moduleId", "courseId"), foreignKeys = arrayOf(
        ForeignKey(
            entity = CourseEntity::class,
            parentColumns = arrayOf("courseId"),
            childColumns = arrayOf("courseId")
        )
    ), indices = arrayOf(Index(value = arrayOf("moduleId")), Index(value = arrayOf("courseId")))
)
data class ModuleEntity(
    @ColumnInfo(name = "moduleId")
    var moduleId: String,
    @ColumnInfo(name = "courseId")
    var courseId: String,
    @ColumnInfo(name = "title")
    var title: String?,
    @ColumnInfo(name = "position")
    var position: Int?,
    @ColumnInfo(name = "read")
    var read: Boolean = false
) {
    @Embedded
    var contentEntity: ContentEntity? = null

}