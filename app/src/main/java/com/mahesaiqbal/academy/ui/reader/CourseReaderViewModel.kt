package com.mahesaiqbal.academy.ui.reader

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.mahesaiqbal.academy.data.source.AcademyRepository
import com.mahesaiqbal.academy.data.source.local.entity.ModuleEntity
import com.mahesaiqbal.academy.vo.Resource

class CourseReaderViewModel(var academyRepository: AcademyRepository) : ViewModel() {

    var courseId: MutableLiveData<String> = MutableLiveData()
    var moduleId: MutableLiveData<String> = MutableLiveData()

    var modules: LiveData<Resource<List<ModuleEntity>>> = Transformations.switchMap(courseId) {
            courseId -> academyRepository.getAllModulesByCourse(courseId)
    }

    fun setCourseIdValue(id: String) {
        courseId.value = id
    }

    var selectedModule: LiveData<Resource<ModuleEntity>> = Transformations.switchMap(moduleId) {
            selectedPosition -> academyRepository.getContent(selectedPosition)
    }

    fun setSelectedModule(id: String) {
        moduleId.value = id
    }

    fun readContent(module: ModuleEntity) {
        academyRepository.setReadModule(module)
    }

    fun getModuleSize(): Int {
        if (modules.value != null) {
            val moduleEntities: List<ModuleEntity>? = modules.value!!.data

            if (moduleEntities != null) {
                return moduleEntities.size
            }
        }

        return 0
    }

    fun setNextPage() {
        if (selectedModule.value != null && modules.value != null) {
            val moduleEntity: ModuleEntity? = selectedModule.value!!.data
            val moduleEntities = modules.value!!.data

            if (moduleEntity != null && moduleEntities != null) {
                val position = moduleEntity.position!!
                if (position < moduleEntities.size && position >= 0) {
                    setSelectedModule(moduleEntities[position + 1].moduleId)
                }
            }
        }
    }

    fun setPrevPage() {
        if (selectedModule.value != null && modules.value != null) {
            val moduleEntity: ModuleEntity? = selectedModule.value!!.data
            val moduleEntities = modules.value!!.data

            if (moduleEntity != null && moduleEntities != null) {
                val position = moduleEntity.position!!
                if (position < moduleEntities.size && position >= 0) {
                    setSelectedModule(moduleEntities[position - 1].moduleId)
                }
            }
        }
    }
}