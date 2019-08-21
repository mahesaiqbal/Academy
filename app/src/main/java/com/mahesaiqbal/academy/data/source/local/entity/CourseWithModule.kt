package com.mahesaiqbal.academy.data.source.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class CourseWithModule(
    @Relation(parentColumn = "courseId", entityColumn = "courseId")
    var modules: List<ModuleEntity>
) {
    @Embedded
    var course: CourseEntity? = null
}