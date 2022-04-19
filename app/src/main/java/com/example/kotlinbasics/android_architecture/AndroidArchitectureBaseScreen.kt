package com.example.kotlinbasics.android_architecture

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.kotlinbasics.R
import com.example.kotlinbasics.android_architecture.mvc_architecture.LoginActivity
import com.example.kotlinbasics.android_architecture.mvp_architecture.CounterActivity
import com.example.kotlinbasics.android_architecture.mvvm_architecture.ChatActivity
import com.example.kotlinbasics.databinding.ActivityAndroidArchitectureBaseScreenBinding

class AndroidArchitectureBaseScreen : AppCompatActivity() {

    private lateinit var binding: ActivityAndroidArchitectureBaseScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAndroidArchitectureBaseScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar
        actionBar?.title = getString(R.string.android_architecture)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnMVPArchitecture.setOnClickListener {
            startActivity(Intent(this@AndroidArchitectureBaseScreen, CounterActivity::class.java))
        }

        binding.btnMVCArchitecture.setOnClickListener {
            startActivity(Intent(this@AndroidArchitectureBaseScreen, LoginActivity::class.java))
        }

        binding.btnMVVMArchitecture.setOnClickListener {
            startActivity(Intent(this@AndroidArchitectureBaseScreen, ChatActivity::class.java))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }
}