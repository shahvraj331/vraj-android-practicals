package com.example.kotlinbasics.ui_layout

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinbasics.R
import com.example.kotlinbasics.commonFolder.utils.Constants.FEEDBACK_DATA_KEY
import com.example.kotlinbasics.commonFolder.utils.Constants.TEN
import com.example.kotlinbasics.databinding.ActivityFeedbackFormBinding
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import android.widget.Spinner
import android.widget.CheckBox

class FeedbackFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFeedbackFormBinding
    private lateinit var nameOfUser: String
    private lateinit var emailOfUser: String
    private var feedbackType: String? = null
    private lateinit var detailedFeedback: String
    private var getResponseOnEmail = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedbackFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar
        actionBar?.title = getString(R.string.constraint_layout)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        val feedbackTypeArray = resources.getStringArray(R.array.feedback_type)

        val adapter = ArrayAdapter(this@FeedbackFormActivity, R.layout.custom_spinner, R.id.tvSelectedFeedbackType, feedbackTypeArray)
        binding.spnFeedbackType.adapter = adapter

        binding.spnFeedbackType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                feedbackType = if (position != 0) {
                    Toast.makeText(this@FeedbackFormActivity, getString(R.string.selected_item, feedbackTypeArray[position]), Toast.LENGTH_SHORT).show()
                    feedbackTypeArray[position]
                } else {
                    null
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {  }
        }

        binding.btnSubmitFeedback.setOnClickListener {
            nameOfUser = binding.etUserName.text.toString().trim()
            emailOfUser = binding.etUserEmail.text.toString().trim()
            detailedFeedback = binding.etDetailedFeedback.text.toString().trim()
            getResponseOnEmail = binding.cbEmailPreferences.isChecked

            if (validateForm()) {
                val dataToSent = FeedbackDataModel(nameOfUser, emailOfUser, feedbackType, detailedFeedback, getResponseOnEmail)
                val showDetailsActivity = Intent(this@FeedbackFormActivity, ShowDataActivity::class.java)
                showDetailsActivity.putExtra(FEEDBACK_DATA_KEY, dataToSent)
                startActivity(showDetailsActivity)
                resetForm(findViewById(R.id.clFeedbackForm))
                binding.etUserName.requestFocus()
            } else {
                return@setOnClickListener
            }
        }
    }

    private fun validateForm(): Boolean {
        if (nameOfUser.isEmpty()) {
            Toast.makeText(this@FeedbackFormActivity, getString(R.string.username_validation), Toast.LENGTH_SHORT).show()
            return false
        }
        if (emailOfUser.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(emailOfUser).matches()) {
            Toast.makeText(this@FeedbackFormActivity, getString(R.string.email_validation), Toast.LENGTH_SHORT).show()
            return false
        }
        if (feedbackType == null) {
            Toast.makeText(this@FeedbackFormActivity, getString(R.string.spinner_validation), Toast.LENGTH_SHORT).show()
            return false
        }
        if (detailedFeedback.isEmpty()) {
            Toast.makeText(this@FeedbackFormActivity, getString(R.string.detailed_feedback_validation), Toast.LENGTH_SHORT).show()
            return false
        } else if (detailedFeedback.count() < TEN) {
            Toast.makeText(this@FeedbackFormActivity, getString(R.string.detailed_feedback_limit_validation), Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }

    private fun resetForm(group: ViewGroup) {
        var currentChildIndex = 0
        val count = group.childCount
        while (currentChildIndex < count) {
            val view = group.getChildAt(currentChildIndex)
            if (view is EditText) {
                view.text.clear()
            } else if (view is Spinner) {
                view.setSelection(0)
            } else if (view is CheckBox) {
                view.isChecked = false
            } else if (view is ViewGroup && view.childCount > 0) {
                resetForm(view)
            }
            ++currentChildIndex
        }
    }
}