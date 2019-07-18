package com.mahesaiqbal.academy.ui.academy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mahesaiqbal.academy.R
import com.mahesaiqbal.academy.data.CourseEntity
import com.mahesaiqbal.academy.utils.DataDummy
import com.mahesaiqbal.academy.ui.academy.AcademyAdapter.AcademyFragmentCallback
import kotlinx.android.synthetic.main.fragment_academy.*

class AcademyFragment : Fragment(), AcademyFragmentCallback {

    lateinit var academyAdapter: AcademyAdapter

    companion object {
        fun newInstance(): Fragment {
            return AcademyFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_academy, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            academyAdapter = AcademyAdapter(activity!!, DataDummy.generateDummyCourses(), this)

            rv_academy.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = academyAdapter
            }
        }
    }

    override fun onItemClick(courseEntity: CourseEntity) {

    }
}
