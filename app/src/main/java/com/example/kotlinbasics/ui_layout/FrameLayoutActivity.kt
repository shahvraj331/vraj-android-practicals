package com.example.kotlinbasics.ui_layout

import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinbasics.R
import com.example.kotlinbasics.databinding.ActivityFrameLayoutBinding

class FrameLayoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFrameLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFrameLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar
        actionBar?.title = getString(R.string.frame_layout)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        val feedbackTypeArray = resources.getStringArray(R.array.feedback_type)

        val adapter = ArrayAdapter(this@FrameLayoutActivity, R.layout.custom_spinner, R.id.tvSelectedFeedbackType, feedbackTypeArray)
        binding.spnFeedbackType.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }
}