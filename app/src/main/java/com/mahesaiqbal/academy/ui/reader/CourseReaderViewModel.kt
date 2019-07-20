package com.mahesaiqbal.academy.ui.reader

import androidx.lifecycle.ViewModel
import com.mahesaiqbal.academy.data.ContentEntity
import com.mahesaiqbal.academy.data.ModuleEntity
import com.mahesaiqbal.academy.utils.DataDummy

class CourseReaderViewModel : ViewModel() {

    lateinit var courseId: String
    lateinit var moduleId: String

    fun setCourseIdValue(id: String) {
        courseId = id
    }

    fun setSelectedModule(id: String) {
        moduleId = id
    }

    fun getSelectedModule(): ModuleEntity? {
        var module: ModuleEntity? = null
        for (i in 0 until getModules().size) {
            if (getModules()[i].moduleId.equals(moduleId)) {
                module = getModules()[i]

                module.contentEntity = ContentEntity("<h3 class=\\\"fr-text-bordered\\\">" + module.title + "</h3><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>")
                break
            }
        }

        return module
    }

    fun getModules(): ArrayList<ModuleEntity> = DataDummy.generateDummyModules(courseId)
}