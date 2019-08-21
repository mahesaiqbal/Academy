package com.mahesaiqbal.academy.ui.academy

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mahesaiqbal.academy.R
import com.mahesaiqbal.academy.data.source.local.entity.CourseEntity
import com.mahesaiqbal.academy.viewmodel.ViewModelFactory
import com.mahesaiqbal.academy.vo.Resource
import com.mahesaiqbal.academy.vo.Status.*
import kotlinx.android.synthetic.main.fragment_academy.*

class AcademyFragment : Fragment() {

    lateinit var academyAdapter: AcademyAdapter
    lateinit var academyViewModel: AcademyViewModel

    var courses: List<CourseEntity> = arrayListOf()

    companion object {
        fun newInstance(): Fragment {
            return AcademyFragment()
        }

        fun obtainViewModel(activity: FragmentActivity): AcademyViewModel {
            val factory = ViewModelFactory.getInstance(activity.application)
            return ViewModelProviders.of(activity, factory).get(AcademyViewModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_academy, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            progress_bar.visibility = View.VISIBLE

            academyViewModel = obtainViewModel(activity!!)

            academyAdapter = AcademyAdapter(activity!!, courses as ArrayList<CourseEntity>)

            academyViewModel.setUsername("Dicoding")
            academyViewModel.courses.observe(this, getCourse)
        }
    }

    private val getCourse = Observer<Resource<List<CourseEntity>>> { courses ->
        if (courses != null) {
            when (courses.status) {
                LOADING -> progress_bar.visibility = View.VISIBLE
                SUCCESS -> {
                    progress_bar.visibility = View.GONE

                    academyAdapter = AcademyAdapter(activity!!, courses.data as ArrayList<CourseEntity>)

                    rv_academy.apply {
                        layoutManager = LinearLayoutManager(context)
                        setHasFixedSize(true)
                        adapter = academyAdapter
                    }
                }
                ERROR -> {
                    progress_bar.visibility = View.GONE
                    Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
