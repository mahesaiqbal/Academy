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

            courseReaderViewModel.getSelectedModule().observe(this, selectedModule)
        }
    }

    private val selectedModule = Observer<ModuleEntity> { module ->
        if (module != null) {
            progress_bar.visibility = View.GONE
            populateWebView(module)
        }
    }

    private fun populateWebView(moduleEntity: ModuleEntity?) {
        web_view.loadData(moduleEntity?.contentEntity!!.content, "text/html", "UTF-8")
    }
}
