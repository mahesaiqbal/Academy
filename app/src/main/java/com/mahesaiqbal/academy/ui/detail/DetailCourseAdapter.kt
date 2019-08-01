package com.mahesaiqbal.academy.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mahesaiqbal.academy.R
import com.mahesaiqbal.academy.data.source.local.entity.ModuleEntity

import com.mahesaiqbal.academy.ui.detail.DetailCourseAdapter.DetailCourseViewHolder
import kotlinx.android.synthetic.main.items_module_list.view.*

class DetailCourseAdapter(var modules: ArrayList<ModuleEntity>) : Adapter<DetailCourseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailCourseViewHolder
            = DetailCourseViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.items_module_list, parent, false))

    override fun onBindViewHolder(holder: DetailCourseViewHolder, position: Int) {
        holder.bindItem(modules[position])
    }

    override fun getItemCount(): Int = modules.size

    inner class DetailCourseViewHolder(itemView: View) : ViewHolder(itemView) {

        fun bindItem(module: ModuleEntity) {
            itemView.text_module_title.text = module.title
        }
    }
}