package com.example.kotlinbasics.recyclerview_and_adapters.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.example.kotlinbasics.R
import com.example.kotlinbasics.databinding.ActivityGridViewBinding
import com.example.kotlinbasics.recyclerview_and_adapters.MenuItems
import com.example.kotlinbasics.recyclerview_and_adapters.SpacesItemDecoration
import com.example.kotlinbasics.recyclerview_and_adapters.adapters.GridViewAdapter

class GridViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGridViewBinding
    private lateinit var menuItems: MutableList<MenuItems>
    private lateinit var foodItemStatus: MutableList<Boolean>
    private lateinit var selectedItems: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGridViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar
        actionBar?.title = getString(R.string.gridlayout_in_recyclerview)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        menuItems = MenuItems.getMenu(applicationContext)
        foodItemStatus = mutableListOf()
        for (index in 0..14) {
            foodItemStatus.add(false)
        }

        val adapter = GridViewAdapter(this@GridViewActivity, menuItems, foodItemStatus)
        binding.rvGridMenuOfFoodItems.adapter = adapter
        val itemDecorator = SpacesItemDecoration(10)
        binding.rvGridMenuOfFoodItems.addItemDecoration(itemDecorator)

        binding.btnSubmitOrder.setOnClickListener {
            foodItemStatus = adapter.getSelectedItems()
            selectedItems = mutableListOf()
            for (itemIndex in 0..14) {
                if (foodItemStatus[itemIndex]) {
                    selectedItems.add(menuItems[itemIndex].nameOfFoodItem)
                }
            }
            if (selectedItems.isEmpty()) {
                Toast.makeText(this@GridViewActivity, getString(R.string.select_food), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                Toast.makeText(this@GridViewActivity, getString(R.string.selected, selectedItems.toString()), Toast.LENGTH_LONG).show()
            }
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }
}