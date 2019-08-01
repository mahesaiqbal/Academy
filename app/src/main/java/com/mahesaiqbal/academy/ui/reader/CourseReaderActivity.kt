package com.mahesaiqbal.academy.ui.reader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import com.mahesaiqbal.academy.R
import com.mahesaiqbal.academy.ui.reader.content.ModuleContentFragment
import com.mahesaiqbal.academy.ui.reader.list.ModuleListFragment
import com.mahesaiqbal.academy.viewmodel.ViewModelFactory

class CourseReaderActivity : AppCompatActivity(), CourseReaderCallback {

    lateinit var courseReaderViewModel: CourseReaderViewModel

    companion object {
        fun obtainViewModel(activity: FragmentActivity): CourseReaderViewModel {
            val factory = ViewModelFactory.getInstance(activity.application)
            return ViewModelProviders.of(activity, factory).get(CourseReaderViewModel::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_reader)

        courseReaderViewModel = obtainViewModel(this)

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            var courseId = bundle.getString("extra_course_id")
            if (courseId != null) {
                Log.d("courseIdReader", courseId)
                courseReaderViewModel.setCourseIdValue(courseId)
                populateFragment()
            }
        }
    }

    override fun moveTo(position: Int, moduleId: String) {
        val fragment: Fragment = ModuleContentFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .add(R.id.frame_container, fragment, ModuleContentFragment.newInstance().tag)
            .addToBackStack(null)
            .commit()
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
        var fragment: Fragment? = supportFragmentManager.findFragmentByTag(ModuleListFragment.newInstance().tag)

        if (fragment == null) {
            fragment = ModuleListFragment.newInstance()
            fragmentTransaction.add(R.id.frame_container, fragment, ModuleListFragment.newInstance().tag)
            fragmentTransaction.addToBackStack(null)
        }

        fragmentTransaction.commit()
    }
}
