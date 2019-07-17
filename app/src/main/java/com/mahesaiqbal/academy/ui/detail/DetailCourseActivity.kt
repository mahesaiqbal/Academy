package com.mahesaiqbal.academy.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import com.mahesaiqbal.academy.R

import kotlinx.android.synthetic.main.activity_detail_course.*

class DetailCourseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_course)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}
