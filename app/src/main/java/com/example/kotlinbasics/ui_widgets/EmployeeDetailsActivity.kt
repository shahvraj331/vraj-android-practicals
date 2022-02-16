package com.example.kotlinbasics.ui_widgets

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinbasics.R
import com.example.kotlinbasics.databinding.ActivityEmployeeDetailsBinding
import android.content.Intent
import android.view.MenuItem
import com.example.kotlinbasics.commonFolder.utils.Constants.DATA_TO_SEND


class EmployeeDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEmployeeDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmployeeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar
        actionBar?.title = getString(R.string.details_title)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val employeeData: EmployeeDetailsModel? = intent.getParcelableExtra(DATA_TO_SEND) as? EmployeeDetailsModel

        binding.tvNameOfEmployee.text = employeeData?.name
        binding.tvEmailOfEmployee.text = employeeData?.email
        binding.tvPhoneOfEmployee.text = employeeData?.phone
        binding.tvGenderOfEmployee.text = employeeData?.gender
        binding.tvSkillsOfEmployee.text = employeeData?.skillsArray.toString()
        if (employeeData?.isInterned == true) {
            binding.tvInternStatusOfEmployee.text = getString(R.string.employee_intern_status_yes)
        } else {
            binding.tvInternStatusOfEmployee.text = getString(R.string.employee_intern_status_no)
        }
        binding.tvDescriptionOfEmployee.text = employeeData?.description
        if (employeeData?.priority == true) {
            binding.tvSubmitPriorityOfEmployee.text = getString(R.string.high)
        } else {
            binding.tvSubmitPriorityOfEmployee.text = getString(R.string.normal)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }
}

