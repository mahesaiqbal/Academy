package com.mahesaiqbal.academy.ui.reader.content

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.mahesaiqbal.academy.R
import com.mahesaiqbal.academy.data.ModuleEntity
import com.mahesaiqbal.academy.ui.reader.CourseReaderViewModel
import kotlinx.android.synthetic.main.fragment_module_content.*

class ModuleContentFragment : Fragment() {

    lateinit var courseReaderViewModel: CourseReaderViewModel

    companion object {
        fun newInstance(): Fragment {
            return ModuleContentFragment()
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
            courseReaderViewModel = ViewModelProviders.of(activity!!).get(CourseReaderViewModel::class.java)
            var module: ModuleEntity? = courseReaderViewModel.getSelectedModule()
            populateWebView(module)
        }
    }

    private fun populateWebView(moduleEntity: ModuleEntity?) {
        web_view.loadData(moduleEntity?.contentEntity!!.content, "text/html", "UTF-8")
    }
}
