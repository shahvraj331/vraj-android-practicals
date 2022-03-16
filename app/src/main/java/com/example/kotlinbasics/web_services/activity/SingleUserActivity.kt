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
import com.example.kotlinbasics.web_services.interfaces.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URL

class SingleUserActivity : AppCompatActivity() {

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
        val retrofitData = ApiInterface.getRetrofitObject().create(ApiInterface::class.java).getData(userID)

        retrofitData.enqueue(object : Callback<SingleUserData> {
            override fun onResponse(call: Call<SingleUserData?>, response: Response<SingleUserData?>) {
                response.body()?.let {
                    lifecycleScope.launch(Dispatchers.IO) {
                        val url = URL(it.data.avatar)
                        val image = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                        withContext(Dispatchers.Main) {
                            updateUI(it, image)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<SingleUserData?>, t: Throwable) { }
        })
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
}