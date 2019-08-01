package com.mahesaiqbal.academy.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ModuleResponse(
    val moduleId: String,
    val courseId: String,
    val title: String,
    val position: Int
) : Parcelable