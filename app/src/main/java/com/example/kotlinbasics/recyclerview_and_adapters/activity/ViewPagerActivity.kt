package com.example.kotlinbasics.recyclerview_and_adapters.activity

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.kotlinbasics.R
import com.example.kotlinbasics.commonFolder.utils.Constants
import com.example.kotlinbasics.databinding.ActivityViewPagerBinding
import com.example.kotlinbasics.recyclerview_and_adapters.MenuItems
import com.example.kotlinbasics.recyclerview_and_adapters.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class ViewPagerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewPagerBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar
        actionBar?.title = getString(R.string.viewpager)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        sharedPreferences = getSharedPreferences(Constants.MENU_SHARED_PREF_KEY, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()

        setViewPagerAdapter()

        TabLayoutMediator(binding.tlMainTab, binding.vpMainViewPager) { tab, position ->
            when(position) {
                0 -> tab.text = getString(R.string.recent_fragment_title)
                1 -> tab.text = getString(R.string.fav_fragment_title)
            }
        }.attach()
    }

    private fun setViewPagerAdapter() {
        binding.vpMainViewPager.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.view_pager_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_clear_list -> {
                MenuItems.removeRecentItems(this@ViewPagerActivity, sharedPreferences, editor)
                setViewPagerAdapter()
                true
            }
            R.id.item_fav_list -> {
                MenuItems.removeFavouriteItems(this@ViewPagerActivity, editor)
                setViewPagerAdapter()
                true
            }
            else -> {
                finish()
                super.onOptionsItemSelected(item)
            }
        }
    }
}