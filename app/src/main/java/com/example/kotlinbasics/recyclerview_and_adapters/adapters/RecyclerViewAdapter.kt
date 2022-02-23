package com.example.kotlinbasics.recyclerview_and_adapters.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinbasics.R
import com.example.kotlinbasics.recyclerview_and_adapters.MenuItems
import com.example.kotlinbasics.recyclerview_and_adapters.RecyclerViewOnClickInterface

class RecyclerViewAdapter
    (private val context: Context, private val menuItems: MutableList<MenuItems>,
     private val isSelected: MutableList<Boolean>, private val onClickInterface: RecyclerViewOnClickInterface
): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val imageOfFoodItem: ImageView = view.findViewById(R.id.ivFoodItemImage)
        val nameOfFoodItem: TextView = view.findViewById(R.id.tvFoodItemName)
        val isFoodItemSelected: CheckBox = view.findViewById(R.id.cbFoodItemOrderStatus)
        val currentFoodItem: ConstraintLayout = view.findViewById(R.id.clCurrentFoodItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.food_menu_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            imageOfFoodItem.setImageResource(menuItems[position].imageOfFoodItem)
            nameOfFoodItem.text = menuItems[position].nameOfFoodItem

            isFoodItemSelected.setOnCheckedChangeListener { _, isChecked ->
                onClickInterface.onItemClick(position, isChecked)
                isSelected[position] = isChecked
            }

            isFoodItemSelected.isChecked = isSelected[position]
            currentFoodItem.setOnLongClickListener {
                onClickInterface.onLongClick(position)
                true
            }
        }
    }

    override fun getItemCount(): Int {
        return menuItems.count()
    }
}