package com.mahesaiqbal.academy.ui.reader.list

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mahesaiqbal.academy.R
import com.mahesaiqbal.academy.ui.reader.CourseReaderCallback
import com.mahesaiqbal.academy.ui.reader.list.ModuleListAdapter.ModuleClickListener
import com.mahesaiqbal.academy.utils.DataDummy
import com.mahesaiqbal.academy.ui.reader.CourseReaderActivity
import kotlinx.android.synthetic.main.fragment_module_list.*

class ModuleListFragment : Fragment(), ModuleClickListener {

    lateinit var moduleListAdapter: ModuleListAdapter
    lateinit var courseReaderCallback: CourseReaderCallback

    companion object {
        fun newInstance(): Fragment {
            return ModuleListFragment()
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
            moduleListAdapter = ModuleListAdapter(DataDummy.generateDummyModules("a14"), this)
            populateRecyclerView()
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        courseReaderCallback = context as CourseReaderActivity
    }

    override fun onItemClick(position: Int, moduleId: String?) {
        courseReaderCallback.moveTo(position, moduleId!!)
    }

    private fun populateRecyclerView() {
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
