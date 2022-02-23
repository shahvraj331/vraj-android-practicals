package com.example.kotlinbasics.recyclerview_and_adapters.activity

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.example.kotlinbasics.R
import com.example.kotlinbasics.commonFolder.utils.Constants.MENU_SHARED_PREF_KEY
import com.example.kotlinbasics.databinding.ActivityRecyclerViewBinding
import com.example.kotlinbasics.recyclerview_and_adapters.MenuItems
import com.example.kotlinbasics.recyclerview_and_adapters.RecyclerViewOnClickInterface
import com.example.kotlinbasics.recyclerview_and_adapters.adapters.RecyclerViewAdapter

class RecyclerViewActivity : AppCompatActivity(), RecyclerViewOnClickInterface {

    private lateinit var binding: ActivityRecyclerViewBinding
    private lateinit var menuItems: MutableList<MenuItems>
    private lateinit var foodItemStatus: MutableList<Boolean>
    private lateinit var selectedItems: MutableList<String>
    private lateinit var favouriteFoodItems: MutableList<MenuItems>
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar
        actionBar?.title = getString(R.string.recyclerview)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        sharedPreferences = getSharedPreferences(MENU_SHARED_PREF_KEY, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        favouriteFoodItems = mutableListOf()

        menuItems = MenuItems.getMenu(applicationContext)
        foodItemStatus = mutableListOf()
        for (index in 0..14) {
            foodItemStatus.add(false)
        }

        val adapter = RecyclerViewAdapter(this@RecyclerViewActivity, menuItems, foodItemStatus, this@RecyclerViewActivity)
        binding.rvMenuOfFoodItems.adapter = adapter

        binding.btnSubmitOrder.setOnClickListener {
            selectedItems = getSelectedFoodItems()

            if (selectedItems.isEmpty()) {
                Toast.makeText(this@RecyclerViewActivity, getString(R.string.select_food), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                Toast.makeText(this@RecyclerViewActivity, getString(R.string.selected, selectedItems.toString()), Toast.LENGTH_LONG).show()
            }

            MenuItems.addRecentItems(
                this@RecyclerViewActivity,
                sharedPreferences,
                editor,
                selectedItems,
                favouriteFoodItems
            )
            finish()
        }
    }

    private fun getSelectedFoodItems(): MutableList<String> {
        val selectedItems: MutableList<String> = mutableListOf()
        for (itemIndex in 0..14) {
            if (foodItemStatus[itemIndex]) {
                selectedItems.add(menuItems[itemIndex].nameOfFoodItem)
            }
        }
        return selectedItems
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }

    override fun onItemClick(position: Int, isChecked: Boolean) {
        foodItemStatus[position] = isChecked
    }

    override fun onLongClick(position: Int) {
        favouriteFoodItems.add(menuItems[position])
        MenuItems.addFavouriteItems(
            this@RecyclerViewActivity,
            sharedPreferences,
            editor,
            favouriteFoodItems
        )
        Toast.makeText(this@RecyclerViewActivity, getString(R.string.fav_selected, menuItems[position].nameOfFoodItem), Toast.LENGTH_SHORT).show()
    }
}