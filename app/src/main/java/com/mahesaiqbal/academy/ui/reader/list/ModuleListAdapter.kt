package com.mahesaiqbal.academy.ui.reader.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mahesaiqbal.academy.R
import com.mahesaiqbal.academy.data.source.local.entity.ModuleEntity
import com.mahesaiqbal.academy.ui.reader.list.ModuleListAdapter.ModuleListViewHolder
import kotlinx.android.synthetic.main.items_module_list_custom.view.*

class ModuleListAdapter(var modules: ArrayList<ModuleEntity>, var listener: ModuleClickListener)
    : Adapter<ModuleListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModuleListViewHolder
            = ModuleListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.items_module_list_custom, parent, false))

    override fun onBindViewHolder(holder: ModuleListViewHolder, position: Int) {
        holder.bindItem(modules[position], position)
    }

    override fun getItemCount(): Int = modules.size

    inner class ModuleListViewHolder(itemView: View) : ViewHolder(itemView) {

        fun bindItem(module: ModuleEntity, position: Int) {
            itemView.text_module_title.text = module.title
            itemView.setOnClickListener { v -> listener.onItemClick(position, module.moduleId) }
        }
    }

    interface ModuleClickListener {
        fun onItemClick(position: Int, moduleId: String?)
    }
}