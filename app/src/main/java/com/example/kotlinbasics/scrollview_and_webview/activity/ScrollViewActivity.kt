package com.example.kotlinbasics.scrollview_and_webview.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.kotlinbasics.R
import com.example.kotlinbasics.databinding.ActivityScrollViewBinding
import com.example.kotlinbasics.recyclerview_and_adapters.MenuItems
import com.example.kotlinbasics.recyclerview_and_adapters.RecyclerViewOnClickInterface
import com.example.kotlinbasics.recyclerview_and_adapters.adapters.RecyclerViewAdapter

class ScrollViewActivity : AppCompatActivity(), RecyclerViewOnClickInterface {

    private lateinit var binding: ActivityScrollViewBinding
    private lateinit var menuItems: MutableList<MenuItems>
    private lateinit var foodItemStatus: MutableList<Boolean>
    private lateinit var selectedItems: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScrollViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar
        actionBar?.title = getString(R.string.scrollview)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        menuItems = MenuItems.getMenu(applicationContext)
        foodItemStatus = mutableListOf()
        for (index in 0..14) {
            foodItemStatus.add(false)
        }

        val adapter = RecyclerViewAdapter(this@ScrollViewActivity, menuItems, foodItemStatus, this@ScrollViewActivity)
        binding.rvMenuOfFoodItems.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
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

    override fun onItemClick(position: Int, isChecked: Boolean) {
        foodItemStatus[position] = isChecked
        selectedItems = getSelectedFoodItems()
        var selectedFoodItemsString = getString(R.string.empty_text)
        for (item in selectedItems) {
            selectedFoodItemsString += getString(R.string.tab_space) + item + getString(R.string.next_line)
        }
        binding.tvSelectedFoodItems.text = selectedFoodItemsString
    }

    override fun onLongClick(position: Int) { }
}