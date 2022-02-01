package com.example.kotlinbasics

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinbasics.databinding.ActivityMainBinding
import com.example.kotlinbasics.recyclerview_and_adapters.activity.DifferentViewsHomeScreenActivity
import com.example.kotlinbasics.ui_widgets.HomeScreenActivity
import com.example.kotlinbasics.ui_layout.LayoutsActivity

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

        binding.btnUILayouts.setOnClickListener {
            val uiLayoutsActivity = Intent(this@MainActivity, LayoutsActivity::class.java)
            startActivity(uiLayoutsActivity)
        }

        binding.btnRecyclerViewAndAdapters.setOnClickListener {
            val recyclerViewActivity = Intent(this@MainActivity, DifferentViewsHomeScreenActivity::class.java)
            startActivity(recyclerViewActivity)
        }
    }
}