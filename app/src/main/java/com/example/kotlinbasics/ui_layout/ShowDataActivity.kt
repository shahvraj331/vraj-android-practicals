package com.example.kotlinbasics.ui_layout

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinbasics.R
import com.example.kotlinbasics.commonFolder.utils.Constants.FEEDBACK_DATA_KEY
import com.example.kotlinbasics.databinding.ActivityShowDataBinding

class ShowDataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShowDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar
        actionBar?.title = getString(R.string.data_activity_title)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        val feedbackData = intent.getParcelableExtra<FeedbackDataModel>(FEEDBACK_DATA_KEY)

        binding.tvNameOfUser.text = feedbackData?.name
        binding.tvEmailOfUser.text = feedbackData?.email
        binding.tvFeedbackTypeOfUser.text = feedbackData?.feedbackType
        binding.tvDetailedFeedbackOfUser.text = feedbackData?.detailedFeedback
        binding.tvEmailResponseUser.text = feedbackData?.getEmailResponse.toString()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }
}