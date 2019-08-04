package com.mahesaiqbal.academy.ui.academy

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mahesaiqbal.academy.R
import com.mahesaiqbal.academy.data.source.local.entity.CourseEntity

import com.mahesaiqbal.academy.ui.academy.AcademyAdapter.AcademyViewHolder
import com.mahesaiqbal.academy.ui.detail.DetailCourseActivity
import kotlinx.android.synthetic.main.items_academy.view.*

class AcademyAdapter(var activity: Activity, var mCourses: ArrayList<CourseEntity>, var callback: AcademyFragmentCallback)
    : Adapter<AcademyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcademyViewHolder
            = AcademyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.items_academy, parent, false))

    override fun onBindViewHolder(holder: AcademyViewHolder, position: Int) {
        holder.bindItem(mCourses[position])
    }

    override fun getItemCount(): Int = mCourses.size

    inner class AcademyViewHolder(itemView: View) : ViewHolder(itemView) {

        fun bindItem(courseEntity: CourseEntity) {
            itemView.tv_item_title.text = courseEntity.title
            itemView.tv_item_description.text = courseEntity.description
            itemView.tv_item_date.text = "Deadline %s".format(courseEntity.deadline)
            itemView.setOnClickListener { v -> detailCourse(courseEntity) }

            Glide.with(activity)
                .load(courseEntity.imagePath)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(itemView.img_poster)
        }

        fun detailCourse(courseEntity: CourseEntity) {
            val intentDetail = Intent(activity, DetailCourseActivity::class.java)
            intentDetail.putExtra("extra_course", courseEntity.courseId)
            activity.startActivity(intentDetail)
        }
    }

    interface AcademyFragmentCallback {
        fun onItemClick(courseEntity: CourseEntity)
    }
}