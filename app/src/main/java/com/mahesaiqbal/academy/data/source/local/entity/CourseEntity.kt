package com.mahesaiqbal.academy.data.source.local.entity

data class CourseEntity(
    val courseId: String,
    val title: String,
    val description: String,
    val deadline: String,
    var bookmarked: Boolean? = false,
    val imagePath: String
)