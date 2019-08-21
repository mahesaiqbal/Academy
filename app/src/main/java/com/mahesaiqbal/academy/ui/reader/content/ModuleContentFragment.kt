package com.mahesaiqbal.academy.ui.reader.content

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mahesaiqbal.academy.R
import com.mahesaiqbal.academy.data.source.local.entity.ModuleEntity
import com.mahesaiqbal.academy.ui.reader.CourseReaderViewModel
import com.mahesaiqbal.academy.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_module_content.*
import android.widget.Toast
import com.mahesaiqbal.academy.data.source.local.entity.ContentEntity
import com.mahesaiqbal.academy.vo.Resource
import com.mahesaiqbal.academy.vo.Status.*

class ModuleContentFragment : Fragment() {

    lateinit var courseReaderViewModel: CourseReaderViewModel

    companion object {
        fun newInstance(): Fragment {
            return ModuleContentFragment()
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
        return inflater.inflate(R.layout.fragment_module_content, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            progress_bar.visibility = View.VISIBLE

            courseReaderViewModel = obtainViewModel(activity!!)

            courseReaderViewModel.selectedModule.observe(this, selectedModule)
        }
    }

    private val selectedModule = Observer<Resource<ModuleEntity>> { moduleEntity ->
        if (moduleEntity != null) {
            when (moduleEntity.status) {
                LOADING -> progress_bar.visibility = View.VISIBLE
                SUCCESS -> if (moduleEntity.data != null) {
                    setButtonNextPrevState(moduleEntity.data)
                    progress_bar.visibility = View.GONE

                    if (!moduleEntity.data.read) {
                        courseReaderViewModel.readContent(moduleEntity.data)
                    }

                    if (moduleEntity.data.contentEntity != null) {
                        populateWebView(moduleEntity.data.contentEntity)
                    }
                }
                ERROR -> {
                    progress_bar.visibility = View.GONE
                    Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                }
            }

            btn_next.setOnClickListener { courseReaderViewModel.setNextPage() }

            btn_prev.setOnClickListener { courseReaderViewModel.setPrevPage() }
        }
    }

    private fun populateWebView(content: ContentEntity?) {
        web_view.loadData(content!!.content, "text/html", "UTF-8")
    }

    private fun setButtonNextPrevState(module: ModuleEntity) {
        if (activity != null) {
            if (module.position == 0) {
                btn_prev.setEnabled(false)
                btn_next.setEnabled(true)
            } else if (module.position == courseReaderViewModel.getModuleSize() - 1) {
                btn_prev.setEnabled(true)
                btn_next.setEnabled(false)
            } else {
                btn_prev.setEnabled(true)
                btn_next.setEnabled(true)
            }
        }
    }
}
