package com.mahesaiqbal.academy.ui.detail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mahesaiqbal.academy.R
import com.mahesaiqbal.academy.data.CourseEntity
import com.mahesaiqbal.academy.data.ModuleEntity
import com.mahesaiqbal.academy.ui.reader.CourseReaderActivity
import com.mahesaiqbal.academy.utils.DataDummy

import kotlinx.android.synthetic.main.activity_detail_course.*
import kotlinx.android.synthetic.main.content_detail_course.*

class DetailCourseActivity : AppCompatActivity() {

    lateinit var detailCourseAdapter: DetailCourseAdapter
    lateinit var detailCourseViewModel: DetailCourseViewModel

    lateinit var modules: List<ModuleEntity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_course)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        detailCourseViewModel = ViewModelProviders.of(this).get(DetailCourseViewModel::class.java)

        val extras: Bundle? = intent.extras
        if (extras != null) {
            val courseId = extras.getString("extra_course")
            if (courseId != null) {
                detailCourseViewModel.setCourseIdValue(courseId)
                Log.d("courseIdDetail", courseId)
                modules = detailCourseViewModel.getModules()
                detailCourseAdapter = DetailCourseAdapter(modules as ArrayList<ModuleEntity>)
            }
        }

        if (detailCourseViewModel.getCourse() != null) {
            populateCourse(detailCourseViewModel.getCourse())
        }

        rv_module.apply {
            val dividerItemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
            addItemDecoration(dividerItemDecoration)
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(this@DetailCourseActivity)
            setHasFixedSize(true)
            adapter = detailCourseAdapter
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
