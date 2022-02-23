package com.example.kotlinbasics.recyclerview_and_adapters.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.kotlinbasics.R
import com.example.kotlinbasics.recyclerview_and_adapters.MenuItems

class RecentItemAdapter(private val context: Context, private val menuItems: MutableList<MenuItems>): BaseAdapter() {

    class ViewHolder(view: View) {
        val recentFoodImage: ImageView = view.findViewById(R.id.ivRecentFoodImage)
        val recentFoodName: TextView = view.findViewById(R.id.tvRecentFoodName)
    }

    override fun getCount(): Int {
        return menuItems.count()
    }

    override fun getItem(position: Int): Any {
        return menuItems[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val currentView = LayoutInflater.from(context).inflate(R.layout.item_recent_fragment, parent, false)
        val viewHolder = ViewHolder(currentView)
        viewHolder.apply {
            recentFoodImage.setImageResource(menuItems[position].imageOfFoodItem)
            recentFoodName.text = menuItems[position].nameOfFoodItem
        }

        return currentView
    }
}