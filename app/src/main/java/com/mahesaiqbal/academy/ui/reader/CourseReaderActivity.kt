package com.mahesaiqbal.academy.ui.reader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mahesaiqbal.academy.R
import com.mahesaiqbal.academy.data.source.local.entity.ModuleEntity
import com.mahesaiqbal.academy.ui.reader.content.ModuleContentFragment
import com.mahesaiqbal.academy.ui.reader.list.ModuleListFragment
import com.mahesaiqbal.academy.viewmodel.ViewModelFactory
import com.mahesaiqbal.academy.vo.Resource
import com.mahesaiqbal.academy.vo.Status.*
import kotlinx.android.synthetic.main.activity_course_reader.*

class CourseReaderActivity : AppCompatActivity(), CourseReaderCallback {

    lateinit var courseReaderViewModel: CourseReaderViewModel

    var isLarge = false

    companion object {
        fun obtainViewModel(activity: FragmentActivity): CourseReaderViewModel {
            val factory = ViewModelFactory.getInstance(activity.application)
            return ViewModelProviders.of(activity, factory).get(CourseReaderViewModel::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_reader)

        supportActionBar?.title = "Course Reader"

        if (frame_list != null) {
            isLarge = true
        }

        courseReaderViewModel = obtainViewModel(this)

        courseReaderViewModel.modules.observe(this, courseReader)

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            var courseId = bundle.getString("extra_course_id")
            if (courseId != null) {
                courseReaderViewModel.setCourseIdValue(courseId)
                populateFragment()
            }
        }
    }

    override fun moveTo(position: Int, moduleId: String) {
        if (!isLarge) {
            val fragment: Fragment = ModuleContentFragment.newInstance()
            supportFragmentManager.beginTransaction()
                .add(R.id.frame_container, fragment, ModuleContentFragment.newInstance().tag)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount <= 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

    private fun populateFragment() {
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()

        if (!isLarge) {
            var fragment: Fragment? = supportFragmentManager.findFragmentByTag(ModuleListFragment.newInstance().tag)

            if (fragment == null) {
                fragment = ModuleListFragment.newInstance()
                fragmentTransaction.add(R.id.frame_container, fragment, ModuleListFragment.newInstance().tag)
                fragmentTransaction.addToBackStack(null)
            }

            fragmentTransaction.commit()
        } else {
            var fragmentList = supportFragmentManager.findFragmentByTag(ModuleListFragment.newInstance().tag)

            if (fragmentList == null) {
                fragmentList = ModuleListFragment.newInstance()
                fragmentTransaction.add(R.id.frame_list, fragmentList, ModuleListFragment.newInstance().tag)
            }

            var fragmentContent = supportFragmentManager.findFragmentByTag(ModuleContentFragment.newInstance().tag)

            if (fragmentContent == null) {
                fragmentContent = ModuleContentFragment.newInstance()
                fragmentTransaction.add(R.id.frame_content, fragmentContent, ModuleContentFragment.newInstance().tag)
            }

            fragmentTransaction.commit()
        }
    }

    private val courseReader = Observer<Resource<List<ModuleEntity>>> { modules ->
        if (modules != null) {
            when (modules.status) {
                LOADING -> {  }
                SUCCESS -> {
                    if (modules.data != null && modules.data.size > 0) {
                        val firstModule = modules.data[0]
                        val isFirstModuleRead = firstModule.read

                        if (!isFirstModuleRead) {
                            val firstModuleId = firstModule.moduleId
                            courseReaderViewModel.setSelectedModule(firstModuleId)
                        } else {
                            val lastReadModuleId = getLastReadFromModules(modules.data)
                            if (lastReadModuleId != null) {
                                courseReaderViewModel.setSelectedModule(lastReadModuleId)
                            }
                        }
                    }
                    removeObserver()
                }
                ERROR -> {
                    Toast.makeText(this, "Gagal menampilkan data.", Toast.LENGTH_SHORT).show()
                    removeObserver()
                }
            }
        }
    }

    private fun getLastReadFromModules(moduleEntities: List<ModuleEntity>): String? {
        var lastReadModule: String? = null
        for (moduleEntity in moduleEntities) {
            if (moduleEntity.read) {
                lastReadModule = moduleEntity.moduleId
                continue
            }
            break
        }
        return lastReadModule
    }

    fun removeObserver() {
        courseReaderViewModel.modules.removeObserver(courseReader)
    }
}
