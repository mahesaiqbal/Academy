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

class HomeActivity : AppCompatActivity() {

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        var fragment: Fragment? = null

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
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        loadFragment(AcademyFragment.newInstance())
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
