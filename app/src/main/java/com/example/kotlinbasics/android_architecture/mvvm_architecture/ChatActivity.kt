package com.example.kotlinbasics.android_architecture.mvvm_architecture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinbasics.R
import com.example.kotlinbasics.databinding.ActivityChatBinding

class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
        binding.viewPager.adapter = ChatAdapter(supportFragmentManager, lifecycle)
    }

    private fun initUI() {
        val actionBar = supportActionBar
        actionBar?.title = getString(R.string.mvvm_architecture)
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }
}