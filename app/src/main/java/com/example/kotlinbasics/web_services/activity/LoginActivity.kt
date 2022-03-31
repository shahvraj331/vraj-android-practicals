package com.example.kotlinbasics.web_services.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.kotlinbasics.R
import com.example.kotlinbasics.commonFolder.utils.Constants
import com.example.kotlinbasics.databinding.ActivityLoginBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar
        actionBar?.title = getString(R.string.login)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmailOfUser.text.toString()
            val password = binding.etPasswordOfUser.text.toString()
            verifyLoginCredentials(email, password)
        }

        binding.btnLogin.setOnLongClickListener {
            verifyLoginCredentials(getString(R.string.valid_email), getString(R.string.valid_password))
            true
        }
    }

    private fun verifyLoginCredentials(email: String, password: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            val credentials = JSONObject()
            credentials.put(getString(R.string.email_text), email)
            credentials.put(getString(R.string.password_text), password)

            val url = URL(Constants.LOGIN_URL)
            val httpURLConnection = url.openConnection() as HttpURLConnection
            httpURLConnection.apply {
                setRequestProperty("Content-type", "application/json")
                requestMethod = getString(R.string.post_method)
                outputStream.bufferedWriter().use {
                    it.write(credentials.toString())
                }
            }

            val isResponseCodeCorrect = httpURLConnection.responseCode == HttpURLConnection.HTTP_OK
            withContext(Dispatchers.Main) {
                if (isResponseCodeCorrect) {
                    Toast.makeText(this@LoginActivity, getString(R.string.login_successful), Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@LoginActivity, UsersListActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@LoginActivity, getString(R.string.login_unsuccessful), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }
}