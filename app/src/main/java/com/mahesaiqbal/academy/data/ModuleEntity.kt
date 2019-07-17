package com.mahesaiqbal.academy.data

class ModuleEntity(
    var moduleId: String?,
    var courseId: String?,
    var title: String?,
    var position: Int?,
    var read: Boolean?
) {
    var contentEntity: ContentEntity? = null

}