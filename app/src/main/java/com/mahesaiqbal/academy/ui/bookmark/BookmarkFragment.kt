package com.mahesaiqbal.academy.ui.bookmark

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mahesaiqbal.academy.R
import com.mahesaiqbal.academy.data.CourseEntity
import com.mahesaiqbal.academy.utils.DataDummy
import com.mahesaiqbal.academy.ui.bookmark.BookmarkAdapter.BookmarkFragmentCallback
import kotlinx.android.synthetic.main.fragment_bookmark.*

class BookmarkFragment : Fragment(), BookmarkFragmentCallback {

    lateinit var bookmarkAdapter: BookmarkAdapter
    lateinit var bookmarkViewModel: BookmarkViewModel

    lateinit var courses: List<CourseEntity>

    companion object {
        fun newInstance(): Fragment {
            return BookmarkFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bookmark, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            bookmarkViewModel = ViewModelProviders.of(this).get(BookmarkViewModel::class.java)
            courses = bookmarkViewModel.getBookmarks()

            bookmarkAdapter = BookmarkAdapter(activity!!, courses as ArrayList<CourseEntity>, this)

            rv_bookmark.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = bookmarkAdapter
            }
        }
    }

    override fun onShareClick(courseEntity: CourseEntity) {
        if (activity != null) {
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder
                .from(activity)
                .setType(mimeType)
                .setChooserTitle("Bagikan aplikasi ini sekarang.")
                .setText("Segera daftar kelas %s di dicoding.com".format(courseEntity.title))
                .startChooser()
        }
    }
}
