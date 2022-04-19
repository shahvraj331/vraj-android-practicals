package com.example.kotlinbasics.web_services.activity

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.lifecycleScope
import com.example.kotlinbasics.R
import com.example.kotlinbasics.databinding.ActivitySingleUserBinding
import com.example.kotlinbasics.web_services.data_classes.SingleUserData
import com.example.kotlinbasics.web_services.interfaces.ApiCallbackListener
import com.example.kotlinbasics.web_services.interfaces.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class SingleUserActivity : AppCompatActivity(), ApiCallbackListener {

    private lateinit var binding: ActivitySingleUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingleUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar
        actionBar?.title = getString(R.string.selected_user)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val selectedUserID = intent.getIntExtra("SELECTED_USER_ID", 0)
        getSelectedUserData(selectedUserID)
    }

    private fun getSelectedUserData(userID: Int) {
        ApiInterface.getSingleUserData(userID, this@SingleUserActivity)
    }

    private fun updateUI(data: SingleUserData, image: Bitmap) {
        binding.ivSelectedUserImage.setImageBitmap(image)
        binding.tvFirstNameOfUser.text = getString(R.string.single_user_first_name, data.data.firstName)
        binding.tvLastNameOfUser.text = getString(R.string.single_user_last_name, data.data.lastName)
        binding.tvEmailOfUser.text = getString(R.string.single_user_email, data.data.email)
        binding.tvIDOfUser.text = getString(R.string.single_user_id, data.data.id.toString())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }

    override fun<T: Any> onSuccess(data: T) {
        val responseData = data as SingleUserData
        lifecycleScope.launch(Dispatchers.IO) {
            val url = URL(responseData.data.avatar)
            val image = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            withContext(Dispatchers.Main) {
                updateUI(responseData, image)
            }
        }
    }

    override fun onFailure() {

    }
}