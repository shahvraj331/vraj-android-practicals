package com.example.kotlinbasics.web_services.activity

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.kotlinbasics.R
import com.example.kotlinbasics.commonFolder.utils.Constants
import com.example.kotlinbasics.databinding.ActivityUsersListBinding
import com.example.kotlinbasics.web_services.adapter.UsersListAdapter
import com.example.kotlinbasics.web_services.data_classes.NewUserDataModel
import com.example.kotlinbasics.web_services.data_classes.NewUserResponseModel
import com.example.kotlinbasics.web_services.data_classes.UsersList
import com.example.kotlinbasics.web_services.interfaces.ApiInterface
import com.example.kotlinbasics.web_services.interfaces.UsersListInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection
import java.net.URL

class UsersListActivity : AppCompatActivity(), UsersListInterface {

    private lateinit var binding: ActivityUsersListBinding
    private lateinit var usersArray: ArrayList<UsersList>
    private lateinit var nameOfNewUser: String
    private lateinit var jobOfNewUser: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar
        actionBar?.title = getString(R.string.list_of_users_title)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        binding.progressBar.visibility = View.VISIBLE
        getListOfUsers()

        binding.fabAddUser.setOnClickListener {
            val dialog = Dialog(this@UsersListActivity)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.create_user_dialog)

            val dialogAttributes = WindowManager.LayoutParams()
            dialogAttributes.width = WindowManager.LayoutParams.MATCH_PARENT
            dialogAttributes.height = WindowManager.LayoutParams.WRAP_CONTENT

            dialog.window?.attributes = dialogAttributes
            val nameOfUser = dialog.findViewById<EditText>(R.id.etNameOfUser)
            val jobOfUser = dialog.findViewById<EditText>(R.id.etJobOfUser)
            val createUser = dialog.findViewById<Button>(R.id.btnCreateUser)

            createUser.setOnClickListener {
                nameOfNewUser = nameOfUser.text.toString().trim()
                jobOfNewUser = jobOfUser.text.toString().trim()

                if (nameOfNewUser.isEmpty() || jobOfNewUser.isEmpty()) {
                    Toast.makeText(this@UsersListActivity, getString(R.string.enter_proper_credentials), Toast.LENGTH_SHORT).show()
                } else {
                    createNewUser()
                }
                dialog.dismiss()
            }
            dialog.show()
        }
    }

    private fun getListOfUsers() {
        lifecycleScope.launch(Dispatchers.IO) {
            val url = URL(Constants.DELAY_RESPONSE_URL)
            val httpURLConnection = url.openConnection() as HttpURLConnection
            httpURLConnection.apply {
                setRequestProperty("Content-type", "application/json")
                requestMethod = getString(R.string.get_method)
            }
            if (httpURLConnection.responseCode == HttpURLConnection.HTTP_OK) {
                var response = httpURLConnection.inputStream.bufferedReader().use {
                    it.readText()
                }
                withContext(Dispatchers.Main) {
                    response = "{\"response\": [$response]}"
                    initializeUsersArray(response)
                    binding.progressBar.visibility = View.GONE
                    setAdapter()
                }
            }
        }
    }

    private fun createNewUser() {
        val retroFit = ApiInterface.getRetrofitObject().create(ApiInterface::class.java).addUser(
            NewUserDataModel(nameOfNewUser,jobOfNewUser)
        )
        retroFit.enqueue(object : Callback<NewUserResponseModel?> {
            override fun onResponse(call: Call<NewUserResponseModel?>, response: Response<NewUserResponseModel?>) {
                response.body()?.let {
                    Toast.makeText(this@UsersListActivity, getString(R.string.user_created, it.toString()), Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<NewUserResponseModel?>, t: Throwable) { }
        })
    }

    private fun initializeUsersArray(response: String) {
        val jsonObject = JSONObject(response)
        val jsonArray = jsonObject.getJSONArray("response")
        var dataJsonArray = JSONArray()
        for (index in 0 until jsonArray.length()) {
            dataJsonArray = jsonArray.getJSONObject(index).get("data") as JSONArray
        }
        usersArray = arrayListOf()

        for (index in 0 until dataJsonArray.length()) {
            usersArray.add(
                UsersList(
                    dataJsonArray.getJSONObject(index).getInt("id"),
                    dataJsonArray.getJSONObject(index).getString("email"),
                    dataJsonArray.getJSONObject(index).getString("first_name"),
                    dataJsonArray.getJSONObject(index).getString("last_name"),
                    dataJsonArray.getJSONObject(index).getString("avatar")
                )
            )
        }
    }

    private fun setAdapter() {
        val adapter = UsersListAdapter(this, usersArray, this)
        binding.rvUsersList.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }

    override fun onUserClicked(idOfUser: Int) {
        val intent = Intent(this@UsersListActivity, SingleUserActivity::class.java)
        intent.putExtra("SELECTED_USER_ID", idOfUser)
        startActivity(intent)
    }
}