package com.example.kotlinbasics

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinbasics.databinding.ActivityMainBinding
import com.example.kotlinbasics.ui_widgets.HomeScreenActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar
        actionBar?.title = getString(R.string.vraj_android_practicals)

        binding.btnUIWidgets.setOnClickListener {
            val uiWidgetsActivity = Intent(this@MainActivity, HomeScreenActivity::class.java)
            startActivity(uiWidgetsActivity)
        }
    }
}