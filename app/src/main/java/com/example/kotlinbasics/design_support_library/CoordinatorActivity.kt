package com.example.kotlinbasics.design_support_library

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinbasics.R
import com.example.kotlinbasics.databinding.ActivityCoordinatorBinding

class CoordinatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoordinatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoordinatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.toolbarLayout.setExpandedTitleColor(resources.getColor(R.color.white))
    }
}