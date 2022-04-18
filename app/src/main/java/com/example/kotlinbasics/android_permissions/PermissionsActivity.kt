package com.example.kotlinbasics.android_permissions

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.kotlinbasics.R
import com.example.kotlinbasics.commonFolder.utils.Constants
import com.example.kotlinbasics.databinding.ActivityPermissionsBinding

class PermissionsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPermissionsBinding
    private lateinit var permissionList: MutableList<String>
    private lateinit var permissionStatus: MutableList<Boolean>
    private lateinit var adapter: PermissionAdapter

    private var cameraResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val currentIntent = result.data
            val bitmap = currentIntent?.extras?.get(getString(R.string.intent_data_keyword)) as Bitmap
            binding.ivCamera.setImageBitmap(bitmap)
        }
    }

    private var galleryResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val currentIntent = result.data
            binding.ivCamera.setImageURI(currentIntent?.data)
        }
    }

    private var pdfResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val currentIntent = result.data
            val data = currentIntent?.data
            binding.tvPDFLocation.text = data?.path.toString()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPermissionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar
        actionBar?.title = getString(R.string.android_permissions)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        permissionList = resources.getStringArray(R.array.permissions_list).toMutableList()
        updatePermissionStatus()
        adapter = PermissionAdapter(this, permissionList, permissionStatus)
        binding.rvPermissionStatus.adapter = adapter

        binding.btnCamera.setOnClickListener {
            if (verifyPermissionFor(Manifest.permission.CAMERA)) {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                cameraResultLauncher.launch(intent)
            }
        }

        binding.btnCamera.setOnLongClickListener {
            if (verifyPermissionFor(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                galleryResultLauncher.launch(intent)
            }
            true
        }

        binding.btnReadFile.setOnClickListener {
            if (verifyPermissionFor(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.type = Constants.PDF_SELECT_INTENT_TYPE
                pdfResultLauncher.launch(intent)
            }
        }

        binding.btnLocation.setOnClickListener {
            verifyPermissionFor(Manifest.permission.ACCESS_COARSE_LOCATION)
        }

        binding.btnContacts.setOnClickListener {
            verifyPermissionFor(Manifest.permission.READ_CONTACTS)
        }
    }

    override fun onResume() {
        super.onResume()
        updatePermissionStatus()
        adapter.updatePermissionStatus(permissionStatus)
    }

    private fun updatePermissionStatus() {
        permissionStatus = mutableListOf()
        permissionStatus.apply {
            add(hasPermissionFor(Manifest.permission.CAMERA))
            add(hasPermissionFor(Manifest.permission.READ_EXTERNAL_STORAGE))
            add(hasPermissionFor(Manifest.permission.ACCESS_COARSE_LOCATION))
            add(hasPermissionFor(Manifest.permission.READ_CONTACTS))
        }
    }

    private fun hasPermissionFor(type: String): Boolean {
        return ActivityCompat.checkSelfPermission(this@PermissionsActivity, type) == PackageManager.PERMISSION_GRANTED
    }

    private fun verifyPermissionFor(type: String): Boolean {
        return if (!hasPermissionFor(type)) {
            ActivityCompat.requestPermissions(this@PermissionsActivity, arrayOf(type),0)
            false
        } else {
            true
        }
    }

    private fun popUpDialog() {
        val builder = AlertDialog.Builder(this@PermissionsActivity)
        builder.setTitle(R.string.dialogTitle)
        builder.setMessage(R.string.open_settings_dialog_message)
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        builder.setPositiveButton(getString(R.string.settings)) { _ , _ ->
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri: Uri = Uri.fromParts(getString(R.string.package_label), packageName, null)
            intent.data = uri
            startActivity(intent)
        }

        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(true)
        alertDialog.show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 0 && grantResults.isNotEmpty()) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this@PermissionsActivity, getString(R.string.permission_granted), Toast.LENGTH_SHORT).show()
                updatePermissionStatus()
                adapter.updatePermissionStatus(permissionStatus)
            } else if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                if (!ActivityCompat.shouldShowRequestPermissionRationale(this@PermissionsActivity, permissions[0])) {
                    popUpDialog()
                    updatePermissionStatus()
                }
            }
        }
    }
}