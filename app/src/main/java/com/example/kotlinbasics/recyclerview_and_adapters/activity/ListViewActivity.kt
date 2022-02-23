package com.example.kotlinbasics.recyclerview_and_adapters.activity

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.Window
import android.widget.Button
import android.widget.EditText
import com.example.kotlinbasics.R
import com.example.kotlinbasics.databinding.ActivityListViewBinding
import java.util.*
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.kotlinbasics.commonFolder.utils.Constants.TEN
import com.example.kotlinbasics.recyclerview_and_adapters.adapters.ListViewAdapter

class ListViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListViewBinding
    private lateinit var contactNamesArray: MutableList<String>
    private lateinit var contactNumber: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar
        actionBar?.title = getString(R.string.listview)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        contactNamesArray = resources.getStringArray(R.array.contact_names).toMutableList()
        contactNumber = resources.getStringArray(R.array.contact_numbers).toMutableList()

        setAdapter()

        binding.lvContactList.setOnItemLongClickListener { _, _, position, _ ->
            val builder = AlertDialog.Builder(this@ListViewActivity)
            builder.setTitle(R.string.dialogTitle)
            builder.setMessage(R.string.remove_permission_string)
            builder.setIcon(android.R.drawable.ic_dialog_alert)

            builder.setPositiveButton(getString(R.string.yes)) { _, _ ->
                contactNamesArray.removeAt(position)
                contactNumber.removeAt(position)
                setAdapter()
            }
            builder.setNegativeButton(getString(R.string.no)) { _, _ -> }

            val alertDialog: AlertDialog = builder.create()
            alertDialog.show()

            return@setOnItemLongClickListener true
        }

        binding.fabAddContact.setOnClickListener {
            val dialog = Dialog(this@ListViewActivity)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.add_contact_dialog)

            val dialogAttributes = WindowManager.LayoutParams()
            dialogAttributes.width = WindowManager.LayoutParams.MATCH_PARENT
            dialogAttributes.height = WindowManager.LayoutParams.WRAP_CONTENT

            dialog.window?.attributes = dialogAttributes
            val newContactName = dialog.findViewById<EditText>(R.id.etNewContactName)
            val newContactNumber = dialog.findViewById<EditText>(R.id.etNewContactNumber)
            val cancelButton = dialog.findViewById<Button>(R.id.btnCancel)
            val addButton = dialog.findViewById<Button>(R.id.btnAddContact)

            addButton.setOnClickListener {
                val name = newContactName.text.toString().trim()
                val number = newContactNumber.text.toString().trim()

                if (validateNameAndNumber(name, number)) {
                    contactNamesArray.add(name)
                    contactNumber.add(number)
                }

                setAdapter()
                dialog.dismiss()
            }

            cancelButton.setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        }
    }

    private fun setAdapter() {
        binding.lvContactList.adapter = ListViewAdapter(this@ListViewActivity, contactNamesArray, contactNumber)
    }

    private fun validateNameAndNumber(name: String, number: String): Boolean {
        val isNameValid: Boolean = if (name.isNotEmpty() && contactNamesArray.contains(name)) {
            Toast.makeText(applicationContext, getString(R.string.name_exists), Toast.LENGTH_SHORT).show()
            false
        } else if (name.isNotEmpty() && !contactNamesArray.contains(name)) {
            true
        } else {
            Toast.makeText(applicationContext, getString(R.string.name_validation), Toast.LENGTH_SHORT).show()
            false
        }

        val isNumberValid: Boolean = if (number.isNotEmpty() && contactNumber.contains(number)) {
            Toast.makeText(applicationContext, getString(R.string.number_exists), Toast.LENGTH_SHORT).show()
            false
        } else if (number.isNotEmpty() && !contactNumber.contains(number) && number.count() == TEN) {
            true
        } else {
            Toast.makeText(applicationContext, getString(R.string.number_validation), Toast.LENGTH_SHORT).show()
            false
        }

        return isNameValid && isNumberValid
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }
}