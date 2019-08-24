package com.mahesaiqbal.academy.ui.bookmark

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mahesaiqbal.academy.R
import com.mahesaiqbal.academy.data.source.local.entity.CourseEntity
import com.mahesaiqbal.academy.ui.detail.DetailCourseActivity
import com.mahesaiqbal.academy.ui.bookmark.BookmarkPagedAdapter.BookmarkViewHolder
import kotlinx.android.synthetic.main.items_bookmark.view.*
import android.annotation.SuppressLint
import android.content.Context
import androidx.recyclerview.widget.DiffUtil

class BookmarkPagedAdapter(var callback: BookmarkFragmentCallback) :
    PagedListAdapter<CourseEntity, BookmarkViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CourseEntity>() {
            override fun areItemsTheSame(oldItem: CourseEntity, newItem: CourseEntity): Boolean {
                return oldItem.courseId == newItem.courseId
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: CourseEntity, newItem: CourseEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder =
        BookmarkViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.items_bookmark,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        val bookmark: CourseEntity = getItem(position)!!
        holder.bindItem(bookmark)
    }

    fun getItemById(swipedPosition: Int): CourseEntity = getItem(swipedPosition)!!

    inner class BookmarkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(bookmark: CourseEntity) {
            itemView.tv_item_title.text = bookmark.title
            itemView.tv_item_description.text = bookmark.description
            itemView.tv_item_date.text = "Deadline %s".format(bookmark.deadline)
            itemView.setOnClickListener { detailCourse(itemView.context, bookmark) }
//            itemView.img_share.setOnClickListener { shareBookmarkCourse(bookmark) }
            itemView.img_share.setOnClickListener { callback.onShareClick(bookmark) }

            Glide.with(itemView.context)
                .load(bookmark.imagePath)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(itemView.img_poster)
        }

        fun detailCourse(context: Context, courseEntity: CourseEntity) {
            val intentDetail = Intent(context, DetailCourseActivity::class.java)
            intentDetail.putExtra("extra_course", courseEntity.courseId)
            context.startActivity(intentDetail)
        }

//        fun shareBookmarkCourse(bookmark: CourseEntity) {
//            val course = CourseEntity(
//                bookmark.courseId,
//                bookmark.title,
//                bookmark.description,
//                bookmark.deadline,
//                false,
//                bookmark.imagePath
//            )
//
//            callback.onShareClick(course)
//        }
    }

    interface BookmarkFragmentCallback {
        fun onShareClick(courseEntity: CourseEntity)
    }
}