package com.mahesaiqbal.academy.ui.reader.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mahesaiqbal.academy.R
import com.mahesaiqbal.academy.data.source.local.entity.ModuleEntity
import com.mahesaiqbal.academy.ui.reader.list.ModuleListAdapter.ModuleViewHolder
import kotlinx.android.synthetic.main.items_module_list_custom.view.*

class ModuleListAdapter(var modules: ArrayList<ModuleEntity>, var listener: ModuleClickListener)
    : Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (viewType == 0) {
            return ModuleViewHolderHide(LayoutInflater.from(parent.context).inflate(R.layout.items_module_list_custom_disable, parent, false));
        } else {
            return ModuleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.items_module_list_custom, parent, false));
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder.itemViewType == 0) {
            val moduleViewHolderHide = holder as ModuleViewHolderHide
            moduleViewHolderHide.bindItem(modules[position])
        } else {
            val moduleViewHolder = holder as ModuleViewHolder
            moduleViewHolder.bindItem(modules[position], position)
            
            moduleViewHolder.itemView.setOnClickListener {
                listener.onItemClick(
                    holder.adapterPosition,
                    modules[moduleViewHolder.adapterPosition].moduleId
                )
            }
        }
    }

    override fun getItemCount(): Int = modules.size

    override fun getItemViewType(position: Int): Int {
        val modulePosition = modules[position].position!!

        return if (modulePosition == 0) {
            1
        }
        else if (modules[modulePosition - 1].read) {
            1
        }
        else {
            0
        }
    }

    inner class ModuleViewHolder(itemView: View) : ViewHolder(itemView) {

        fun bindItem(module: ModuleEntity, position: Int) {
            itemView.text_module_title.text = module.title
            itemView.setOnClickListener { listener.onItemClick(position, module.moduleId) }
        }
    }

    inner class ModuleViewHolderHide(itemView: View) : ViewHolder(itemView) {
        fun bindItem(module: ModuleEntity) {
            itemView.text_module_title.text = module.title
        }
    }

    interface ModuleClickListener {
        fun onItemClick(position: Int, moduleId: String?)
    }
}