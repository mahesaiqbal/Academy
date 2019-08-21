package com.mahesaiqbal.academy.ui.bookmark

import android.os.Bundle
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
import kotlinx.android.synthetic.main.fragment_bookmark.*

class BookmarkFragment : Fragment() {

    lateinit var bookmarkAdapter: BookmarkAdapter
    lateinit var bookmarkViewModel: BookmarkViewModel

    var courses: List<CourseEntity> = arrayListOf()

    companion object {
        fun newInstance(): Fragment {
            return BookmarkFragment()
        }

        fun obtainViewModel(activity: FragmentActivity): BookmarkViewModel {
            val factory = ViewModelFactory.getInstance(activity.application)
            return ViewModelProviders.of(activity, factory).get(BookmarkViewModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bookmark, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            progress_bar.visibility = View.VISIBLE

            bookmarkViewModel = obtainViewModel(activity!!)

            bookmarkAdapter = BookmarkAdapter(activity!!, courses as ArrayList<CourseEntity>)

            bookmarkViewModel.getBookmarks().observe(this, getBookmark)
        }
    }

    private val getBookmark = Observer<Resource<List<CourseEntity>>> { courses ->
        if (courses != null) {
            when (courses.status) {
                LOADING -> progress_bar.visibility = View.VISIBLE
                SUCCESS -> {
                    progress_bar.visibility = View.GONE

                    bookmarkAdapter = BookmarkAdapter(activity!!, courses.data as ArrayList<CourseEntity>)

                    rv_bookmark.apply {
                        layoutManager = LinearLayoutManager(context)
                        setHasFixedSize(true)
                        adapter = bookmarkAdapter
                    }
                }
                ERROR -> {
                    progress_bar.visibility = View.GONE
                    Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

//    override fun onShareClick(courseEntity: CourseEntity) {
//        if (activity != null) {
//            val mimeType = "text/plain"
//            ShareCompat.IntentBuilder
//                .from(activity)
//                .setType(mimeType)
//                .setChooserTitle("Bagikan aplikasi ini sekarang.")
//                .setText("Segera daftar kelas %s di dicoding.com".format(courseEntity.title))
//                .startChooser()
//        }
//    }
}
