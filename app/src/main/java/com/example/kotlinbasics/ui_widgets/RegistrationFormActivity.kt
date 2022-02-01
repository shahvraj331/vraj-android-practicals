package com.example.kotlinbasics.ui_widgets

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinbasics.R
import com.example.kotlinbasics.commonFolder.utils.Constants.DATA_TO_SEND
import com.example.kotlinbasics.commonFolder.utils.Constants.TEN
import com.example.kotlinbasics.commonFolder.utils.Constants.TWO
import com.example.kotlinbasics.commonFolder.utils.Constants.ZERO
import com.example.kotlinbasics.databinding.ActivityRegistrationFormBinding
import com.example.kotlinbasics.databinding.CustomToastBinding

class RegistrationFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationFormBinding
    private lateinit var toastBinding: CustomToastBinding
    private lateinit var fullNameOfEmployee: String
    private lateinit var emailOfEmployee: String
    private  lateinit var phoneOfEmployee: String
    private lateinit var employeeDescription: String
    private lateinit var genderOfEmployee: String
    private lateinit var selectedSkillsArray: MutableList<String>
    private var isInterned: Boolean = false
    private var isHighPriorityEmployee: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar
        actionBar?.title = getString(R.string.reg_form)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        binding.rgGenderType.setOnCheckedChangeListener { _ , checkedID ->
            val selectedRadioButton: RadioButton = findViewById(checkedID)
            genderOfEmployee = selectedRadioButton.text.toString()
            Toast.makeText(this@RegistrationFormActivity, getString(R.string.selected, genderOfEmployee), Toast.LENGTH_SHORT).show()
        }

        binding.btnSubmit.setOnClickListener {
            fullNameOfEmployee = binding.etFullName.text.toString().trim()
            emailOfEmployee = binding.etEmail.text.toString().trim()
            phoneOfEmployee = binding.etPhone.text.toString().trim()
            val selectedGenderID = binding.rgGenderType.checkedRadioButtonId
            val selectedRadioButton: RadioButton = findViewById(selectedGenderID)
            genderOfEmployee = selectedRadioButton.text.toString()
            selectedSkillsArray = getAllSkills()
            isInterned = binding.tbInternedOrNot.isChecked
            employeeDescription = binding.etDescriptionOfEmployee.text.toString().trim()
            isHighPriorityEmployee = binding.swSubmitPriority.isChecked

            if (!validateForm()) {
                return@setOnClickListener
            }

            if (!isHighPriorityEmployee) {
                val builder = AlertDialog.Builder(this@RegistrationFormActivity)
                builder.setTitle(R.string.dialogTitle)
                builder.setMessage(R.string.dialogMessage)
                builder.setIcon(android.R.drawable.ic_dialog_alert)

                builder.setPositiveButton(getString(R.string.yes)) { _ , _ ->
                    isHighPriorityEmployee = true
                    viewCustomToast()
                    intentToEmployeeDetails()
                }
                builder.setNeutralButton(getString(R.string.cancel)) { _ , _ -> }
                builder.setNegativeButton(getString(R.string.no)) { _ , _ ->
                    viewCustomToast()
                    intentToEmployeeDetails()
                }

                val alertDialog: AlertDialog = builder.create()
                alertDialog.setCancelable(true)
                alertDialog.show()
            } else {
                viewCustomToast()
                intentToEmployeeDetails()
            }
        }
    }

    private fun validateForm(): Boolean {
        if (fullNameOfEmployee.isEmpty()) {
            Toast.makeText(this@RegistrationFormActivity, getString(R.string.full_name), Toast.LENGTH_SHORT).show()
            return false
        }

        if (emailOfEmployee.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(emailOfEmployee).matches()) {
            Toast.makeText(this@RegistrationFormActivity, getString(R.string.enter_proper_email), Toast.LENGTH_SHORT).show()
            return false
        }

        if (phoneOfEmployee.isEmpty() || phoneOfEmployee.count() < TEN) {
            Toast.makeText(this@RegistrationFormActivity, getString(R.string.phone_validation), Toast.LENGTH_SHORT).show()
            return false
        }

        if (selectedSkillsArray.count() < TWO) {
            Toast.makeText(this@RegistrationFormActivity, getString(R.string.minimum_skills), Toast.LENGTH_SHORT).show()
            return false
        }

        if (employeeDescription.isEmpty()) {
            Toast.makeText(this@RegistrationFormActivity, getString(R.string.describe_text), Toast.LENGTH_SHORT).show()
            return false
        } else if (employeeDescription.isNotEmpty() && employeeDescription.count() <= TEN) {
            Toast.makeText(this@RegistrationFormActivity, getString(R.string.describe_limit), Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    private fun getAllSkills(): MutableList<String> {
        var skillName: String
        val selectedSkillsArray = mutableListOf<String>()
        if (binding.cbHTML.isChecked) {
            skillName = binding.cbHTML.text.toString()
            selectedSkillsArray.add(skillName)
        }
        if (binding.cbCSS.isChecked) {
            skillName = binding.cbCSS.text.toString()
            selectedSkillsArray.add(skillName)
        }
        if (binding.cbAndroid.isChecked) {
            skillName = binding.cbAndroid.text.toString()
            selectedSkillsArray.add(skillName)
        }
        if (binding.cbIOS.isChecked) {
            skillName = binding.cbIOS.text.toString()
            selectedSkillsArray.add(skillName)
        }
        if (binding.cbFlutter.isChecked) {
            skillName = binding.cbFlutter.text.toString()
            selectedSkillsArray.add(skillName)
        }
        if (binding.cbJavaScript.isChecked) {
            skillName = binding.cbJavaScript.text.toString()
            selectedSkillsArray.add(skillName)
        }
        return selectedSkillsArray
    }

    private fun viewCustomToast() {
        toastBinding = CustomToastBinding.inflate(layoutInflater)
        toastBinding.tvCustomToastMessage.text = getString(R.string.submit_toast_text)
        val myToast = Toast(applicationContext)
        myToast.duration = Toast.LENGTH_SHORT
        myToast.setGravity(Gravity.BOTTOM, ZERO, ZERO)
        myToast.view = toastBinding.root
        myToast.show()
    }

    private fun intentToEmployeeDetails() {
        val intentData = EmployeeDetailsModel(fullNameOfEmployee, genderOfEmployee, emailOfEmployee, phoneOfEmployee, isInterned, employeeDescription, isHighPriorityEmployee
                                                , selectedSkillsArray)
        val goToEmployeeDetailsActivity = Intent(this@RegistrationFormActivity, EmployeeDetailsActivity::class.java)
        goToEmployeeDetailsActivity.putExtra(DATA_TO_SEND, intentData)
        startActivity(goToEmployeeDetailsActivity)
        clearOutFormAttributes()
    }

    private fun clearOutFormAttributes() {
        binding.etFullName.setText(getString(R.string.empty_text))
        binding.etEmail.setText(R.string.empty_text)
        binding.etPhone.setText(R.string.empty_text)
        binding.etDescriptionOfEmployee.setText(R.string.empty_text)
        binding.tbInternedOrNot.isChecked = false
        binding.swSubmitPriority.isChecked = false
        binding.cbHTML.isChecked = false
        binding.cbCSS.isChecked = false
        binding.cbAndroid.isChecked = false
        binding.cbIOS.isChecked = false
        binding.cbFlutter.isChecked = false
        binding.cbJavaScript.isChecked = false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }
}

