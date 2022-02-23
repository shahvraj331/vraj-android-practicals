package com.example.kotlinbasics.recyclerview_and_adapters.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.kotlinbasics.R
import com.example.kotlinbasics.databinding.ActivityBottomNavViewPagerBinding
import com.example.kotlinbasics.recyclerview_and_adapters.adapters.BottomNavAdapter
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.kotlinbasics.commonFolder.utils.Constants.ONE
import com.example.kotlinbasics.commonFolder.utils.Constants.TWO
import com.example.kotlinbasics.commonFolder.utils.Constants.ZERO

class BottomNavViewPagerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBottomNavViewPagerBinding
    private var currentMenuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavViewPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar
        actionBar?.title = getString(R.string.bottom_navigation)

        val myAdapter = BottomNavAdapter(supportFragmentManager, lifecycle)
        binding.viewPager.adapter = myAdapter

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bnHome -> binding.viewPager.currentItem = ZERO
                R.id.bnSearch -> binding.viewPager.currentItem = ONE
                R.id.bnMyProfile -> binding.viewPager.currentItem = TWO
            }
            true
        }

        binding.viewPager.registerOnPageChangeCallback(object: OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                currentMenuItem?.isChecked = false
                binding.bottomNavigationView.menu.getItem(position).isChecked = true
                currentMenuItem = binding.bottomNavigationView.menu.getItem(position)
            }
        })
    }
}