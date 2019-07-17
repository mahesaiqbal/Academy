package com.mahesaiqbal.academy.data

data class CourseEntity(
    val courseId: String,
    val title: String,
    val description: String,
    val deadline: String,
    var bookmarked: Boolean? = false,
    val imagePath: String
)