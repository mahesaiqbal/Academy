package com.mahesaiqbal.academy.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CourseResponse(
    val id: String,
    val title: String,
    val description: String,
    val date: String,
    val imagePath: String
) : Parcelable