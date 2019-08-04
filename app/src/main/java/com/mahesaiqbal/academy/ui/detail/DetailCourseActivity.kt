package com.mahesaiqbal.academy.ui.detail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mahesaiqbal.academy.R
import com.mahesaiqbal.academy.data.source.local.entity.CourseEntity
import com.mahesaiqbal.academy.data.source.local.entity.ModuleEntity
import com.mahesaiqbal.academy.ui.reader.CourseReaderActivity
import com.mahesaiqbal.academy.viewmodel.ViewModelFactory

import kotlinx.android.synthetic.main.activity_detail_course.*
import kotlinx.android.synthetic.main.content_detail_course.*

class DetailCourseActivity : AppCompatActivity() {

    lateinit var detailCourseAdapter: DetailCourseAdapter
    lateinit var detailCourseViewModel: DetailCourseViewModel

    lateinit var modules: List<ModuleEntity>

    companion object {
        fun obtainViewModel(activity: AppCompatActivity): DetailCourseViewModel {
            val factory = ViewModelFactory.getInstance(activity.application)
            return ViewModelProviders.of(activity, factory).get(DetailCourseViewModel::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_course)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        detailCourseViewModel = obtainViewModel(this)

        val extras: Bundle? = intent.extras
        if (extras != null) {
            val courseId = extras.getString("extra_course")
            if (courseId != null) {
                progress_bar.visibility = View.VISIBLE
                detailCourseViewModel.setCourseIdValue(courseId)
//                modules = detailCourseViewModel.getModules()
//                detailCourseAdapter = DetailCourseAdapter(modules as ArrayList<ModuleEntity>)
            }
        }

        detailCourseViewModel.getModules().observe(this, getModule)

        detailCourseViewModel.getCourse().observe(this, course)
    }

    private val getModule = Observer<List<ModuleEntity>> { module ->
        if (module != null) {
            progress_bar.visibility = View.GONE
            detailCourseAdapter = DetailCourseAdapter(module as ArrayList<ModuleEntity>)

            rv_module.apply {
                val dividerItemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
                addItemDecoration(dividerItemDecoration)
                isNestedScrollingEnabled = false
                layoutManager = LinearLayoutManager(this@DetailCourseActivity)
                setHasFixedSize(true)
                adapter = detailCourseAdapter
            }

            detailCourseAdapter.notifyDataSetChanged()
        }
    }

    private val course = Observer<CourseEntity> { courseEntity ->
        if (courseEntity != null) {
            populateCourse(courseEntity)
        }
    }

    fun populateCourse(courseEntity: CourseEntity) {
        text_title.text = courseEntity.title
        text_desc.text = courseEntity.description
        text_date.text = "Deadline %s".format(courseEntity.deadline)

        Glide.with(this)
            .load(courseEntity.imagePath)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
            .into(image_poster)

        btn_start.setOnClickListener { v -> goToCourseReader() }
    }

    fun goToCourseReader() {
        val intent = Intent(this, CourseReaderActivity::class.java)
        intent.putExtra("extra_course_id", detailCourseViewModel.getCourseIdValue())
        startActivity(intent)
    }

}
