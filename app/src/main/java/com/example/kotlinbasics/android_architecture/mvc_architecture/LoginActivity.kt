package com.example.kotlinbasics.android_architecture.mvc_architecture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.kotlinbasics.R
import com.example.kotlinbasics.databinding.ActivityLoginTestBinding

class LoginActivity : AppCompatActivity(), LoginResultInterface {

    private lateinit var binding: ActivityLoginTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar
        actionBar?.title = getString(R.string.login_title)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnSubmit.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            validateCredentials()
        }
    }

    private fun validateCredentials() {
        val userName = binding.etUserName.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        val loginAPI = LoginApi(userName, password, this)
        loginAPI.doLogin()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }

    override fun onLoginResult(result: Boolean) {
        binding.progressBar.visibility = View.INVISIBLE
        if (result) {
            Toast.makeText(this@LoginActivity, getString(R.string.login_success), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this@LoginActivity, getString(R.string.login_failure), Toast.LENGTH_SHORT).show()
        }
    }
}