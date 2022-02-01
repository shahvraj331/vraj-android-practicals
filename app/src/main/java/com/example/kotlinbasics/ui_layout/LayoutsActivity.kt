package com.example.kotlinbasics.ui_layout

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinbasics.R
import com.example.kotlinbasics.databinding.ActivityLayoutsBinding

class LayoutsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLayoutsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLayoutsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar
        actionBar?.title = getString(R.string.layouts_activity_title)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnConstraintLayout.setOnClickListener {
            val feedbackFormActivity = Intent(this@LayoutsActivity, FeedbackFormActivity::class.java)
            startActivity(feedbackFormActivity)
        }

        binding.btnLinearLayout.setOnClickListener {
            val feedbackFormActivity = Intent(this@LayoutsActivity, LinearLayoutActivity::class.java)
            startActivity(feedbackFormActivity)
        }

        binding.btnRelativeLayout.setOnClickListener {
            val feedbackFormActivity = Intent(this@LayoutsActivity, RelativeLayoutActivity::class.java)
            startActivity(feedbackFormActivity)
        }

        binding.btnTableLayout.setOnClickListener {
            val feedbackFormActivity = Intent(this@LayoutsActivity, TableLayoutActivity::class.java)
            startActivity(feedbackFormActivity)
        }

        binding.btnFrameLayout.setOnClickListener {
            val feedbackFormActivity = Intent(this@LayoutsActivity, FrameLayoutActivity::class.java)
            startActivity(feedbackFormActivity)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }
}