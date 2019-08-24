package com.mahesaiqbal.academy.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
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
import com.mahesaiqbal.academy.vo.Resource
import com.mahesaiqbal.academy.vo.Status.*

import kotlinx.android.synthetic.main.activity_detail_course.*
import kotlinx.android.synthetic.main.content_detail_course.*
import com.mahesaiqbal.academy.data.source.local.entity.CourseWithModule
import androidx.core.content.ContextCompat

class DetailCourseActivity : AppCompatActivity() {

    lateinit var detailCourseAdapter: DetailCourseAdapter
    lateinit var detailCourseViewModel: DetailCourseViewModel

    lateinit var modules: List<ModuleEntity>

    lateinit var menuDetail: Menu

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
        supportActionBar?.apply {
            title = "Detail Course"
            setDisplayHomeAsUpEnabled(true)
        }

        detailCourseViewModel = obtainViewModel(this)

        val extras: Bundle? = intent.extras
        if (extras != null) {
            val courseId = extras.getString("extra_course")
            if (courseId != null) {
                progress_bar.visibility = View.VISIBLE
                detailCourseViewModel.setCourseIdValue(courseId)
            }
        }

        detailCourseViewModel.courseModule.observe(this, courseWithModuleResource)
    }

    private val courseWithModuleResource = Observer<Resource<CourseWithModule>> { courseWithModuleResource ->
        if (courseWithModuleResource != null) {

            when (courseWithModuleResource.status) {
                LOADING -> progress_bar.visibility = View.VISIBLE
                SUCCESS -> if (courseWithModuleResource.data != null) {
                    progress_bar.visibility = View.GONE

                    detailCourseAdapter = DetailCourseAdapter(courseWithModuleResource.data.modules as ArrayList<ModuleEntity>)

                    rv_module.apply {
                        val dividerItemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
                        addItemDecoration(dividerItemDecoration)
                        layoutManager = LinearLayoutManager(context)
                        setHasFixedSize(true)
                        adapter = detailCourseAdapter
                    }

                    populateCourse(courseWithModuleResource.data.course!!)
                }
                ERROR -> progress_bar.visibility = View.GONE
            }
        }
    }

    private val courseWithModule = Observer<Resource<CourseWithModule>> { courseWithModule ->
        if (courseWithModule != null) {

            when (courseWithModule.status) {
                LOADING -> progress_bar.visibility = View.VISIBLE
                SUCCESS -> if (courseWithModule.data != null) {
                    progress_bar.visibility = View.GONE

                    val state = courseWithModule.data.course!!.bookmarked
                    setBookmarkState(state)
                }
                ERROR -> {
                    progress_bar.visibility = View.GONE
                    Toast.makeText(getApplicationContext(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                }
            }
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

        btn_start.setOnClickListener { goToCourseReader() }
    }

    fun goToCourseReader() {
        val intent = Intent(this, CourseReaderActivity::class.java)
        intent.putExtra("extra_course_id", detailCourseViewModel.getCourseIdValue())
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        menuDetail = menu!!

        detailCourseViewModel.courseModule.observe(this, courseWithModule)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.action_bookmark) {
            detailCourseViewModel.setBookmark()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setBookmarkState(state: Boolean) {
        val menuItem = menuDetail.findItem(R.id.action_bookmark)
        if (state) {
            menuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_bookmarked_white))
        } else {
            menuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_bookmark_white))
        }
    }
}
