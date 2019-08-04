package com.mahesaiqbal.academy.ui.reader.list

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mahesaiqbal.academy.R
import com.mahesaiqbal.academy.data.source.local.entity.ModuleEntity
import com.mahesaiqbal.academy.ui.reader.CourseReaderCallback
import com.mahesaiqbal.academy.ui.reader.list.ModuleListAdapter.ModuleClickListener
import com.mahesaiqbal.academy.ui.reader.CourseReaderActivity
import com.mahesaiqbal.academy.ui.reader.CourseReaderViewModel
import com.mahesaiqbal.academy.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_module_list.*

class ModuleListFragment : Fragment(), ModuleClickListener {

    lateinit var moduleListAdapter: ModuleListAdapter
    lateinit var courseReaderCallback: CourseReaderCallback

    lateinit var courseReaderViewModel: CourseReaderViewModel

    companion object {
        fun newInstance(): Fragment {
            return ModuleListFragment()
        }

        fun obtainViewModel(activity: FragmentActivity): CourseReaderViewModel {
            val factory = ViewModelFactory.getInstance(activity.application)
            return ViewModelProviders.of(activity, factory).get(CourseReaderViewModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_module_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            progress_bar.visibility = View.VISIBLE

            courseReaderViewModel = obtainViewModel(activity!!)

            courseReaderViewModel.getModules().observe(this, getModule)
        }
    }

    private val getModule = Observer<List<ModuleEntity>> { modules ->
        if (modules != null) {
            progress_bar.visibility = View.GONE
            moduleListAdapter = ModuleListAdapter(modules as ArrayList<ModuleEntity>, this)
            populateRecyclerView(modules)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        courseReaderCallback = context as CourseReaderActivity
    }

    override fun onItemClick(position: Int, moduleId: String?) {
        courseReaderCallback.moveTo(position, moduleId!!)
        courseReaderViewModel.setSelectedModule(moduleId)
    }

    private fun populateRecyclerView(modules: List<ModuleEntity>) {
        progress_bar.visibility = View.GONE

        rv_module.apply {
            val dividerItemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
            addItemDecoration(dividerItemDecoration)
            layoutManager = LinearLayoutManager(this.context)
            setHasFixedSize(true)
            adapter = moduleListAdapter
        }
    }
}
