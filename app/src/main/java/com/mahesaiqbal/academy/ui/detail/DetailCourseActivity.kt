package com.mahesaiqbal.academy.ui.detail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mahesaiqbal.academy.R
import com.mahesaiqbal.academy.data.CourseEntity
import com.mahesaiqbal.academy.ui.reader.CourseReaderActivity
import com.mahesaiqbal.academy.utils.DataDummy

import kotlinx.android.synthetic.main.activity_detail_course.*
import kotlinx.android.synthetic.main.content_detail_course.*

class DetailCourseActivity : AppCompatActivity() {

    lateinit var detailCourseAdapter: DetailCourseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_course)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val extras: Bundle? = intent.extras
        if (extras != null) {
            var courseId = extras.getString("extra_course")
            if (courseId != null) {
                detailCourseAdapter = DetailCourseAdapter(DataDummy.generateDummyModules(courseId))

                populateCourse(courseId)
            }
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

    fun populateCourse(courseId: String) {
        val courseEntity: CourseEntity? = DataDummy.getCourse(courseId)
        text_title.text = courseEntity?.title
        text_desc.text = courseEntity?.description
        text_date.text = "Deadline %s".format(courseEntity?.deadline)

        Glide.with(this)
            .load(courseEntity?.imagePath)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
            .into(image_poster)

        btn_start.setOnClickListener { v -> goToCourseReader(courseId) }
    }

    fun goToCourseReader(id: String) {
        val intent = Intent(this, CourseReaderActivity::class.java)
        intent.putExtra("extra_course_id", id)
        startActivity(intent)
    }

}
