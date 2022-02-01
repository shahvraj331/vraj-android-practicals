package com.example.kotlinbasics.recyclerview_and_adapters.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinbasics.R
import com.example.kotlinbasics.recyclerview_and_adapters.MenuItems

class GridViewAdapter(private val context: Context, private val menuItems: MutableList<MenuItems>,
                      private val isSelected: MutableList<Boolean>): RecyclerView.Adapter<GridViewAdapter.ViewHolder>(){
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val imageOfFoodItem: ImageView = view.findViewById(R.id.ivCurrentFoodImage)
        val nameOfFoodItem: TextView = view.findViewById(R.id.tvCurrentFoodName)
        val selectedLogo: ImageView = view.findViewById(R.id.ivSelectedLogo)
        val selectedCardView: CardView = view.findViewById(R.id.cvCurrentFoodItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.menu_item_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            imageOfFoodItem.setImageResource(menuItems[position].imageOfFoodItem)
            nameOfFoodItem.text = menuItems[position].nameOfFoodItem
            selectedCardView.setOnClickListener {
                if (selectedLogo.visibility == View.INVISIBLE) {
                    selectedLogo.visibility = View.VISIBLE
                    isSelected[position] = true
                } else {
                    selectedLogo.visibility = View.INVISIBLE
                    isSelected[position] = false
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return menuItems.count()
    }

    fun getSelectedItems(): MutableList<Boolean> {
        return isSelected
    }
}