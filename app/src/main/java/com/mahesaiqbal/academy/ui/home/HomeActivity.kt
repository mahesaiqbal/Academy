package com.mahesaiqbal.academy.ui.home

import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.mahesaiqbal.academy.R
import com.mahesaiqbal.academy.ui.academy.AcademyFragment
import com.mahesaiqbal.academy.ui.bookmark.BookmarkFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.action_home -> {
                loadFragment(AcademyFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.action_bookmark -> {
                loadFragment(BookmarkFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
        }

        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        nav_view.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        if (savedInstanceState != null) {
            savedInstanceState.getInt("selected_menu")
        } else {
            nav_view.selectedItemId = R.id.action_home
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putInt("selected_menu", nav_view.selectedItemId)
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.container, fragment)
            .commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val main = Intent(Intent.ACTION_MAIN)
        main.addCategory(Intent.CATEGORY_LAUNCHER)
        main.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(main)
    }
}
