package com.example.kotlinbasics.recyclerview_and_adapters.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.kotlinbasics.R
import com.example.kotlinbasics.commonFolder.utils.Constants
import com.example.kotlinbasics.recyclerview_and_adapters.MenuItems
import com.example.kotlinbasics.recyclerview_and_adapters.adapters.RecentItemAdapter

class FavouriteItemsFragment : Fragment() {

    private var sharedPreferences: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null
    private lateinit var favouriteMenuItems: MutableList<MenuItems>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_second, container, false)
        sharedPreferences = activity?.getSharedPreferences(Constants.MENU_SHARED_PREF_KEY, Context.MODE_PRIVATE)
        editor = sharedPreferences?.edit()
        val favouriteList = view.findViewById<ListView>(R.id.lvFavourite)
        val emptyListText = view.findViewById<TextView>(R.id.tvEmptyListText)
        favouriteList.adapter = updateDataAndGetAdapter()
        favouriteList.emptyView = emptyListText

        favouriteList.setOnItemClickListener { _, _, position, _ ->
            val builder = activity?.let { AlertDialog.Builder(it) }
            builder?.setTitle(R.string.dialogTitle)
            builder?.setMessage(R.string.remove_food_permission)
            builder?.setIcon(android.R.drawable.ic_dialog_alert)

            builder?.setPositiveButton(getString(R.string.yes)) { _, _ ->
                val selectedFoodItem = favouriteMenuItems[position].nameOfFoodItem
                editor?.putString(selectedFoodItem, getString(R.string.no))
                editor?.apply()
                favouriteList.adapter = updateDataAndGetAdapter()
            }
            builder?.setNegativeButton(getString(R.string.no)) { _, _ -> }

            val alertDialog: AlertDialog? = builder?.create()
            alertDialog?.show()
        }

        return view
    }

    private fun updateDataAndGetAdapter(): RecentItemAdapter? {
        favouriteMenuItems =
            MenuItems.retrieveFavouriteItems(activity?.applicationContext, sharedPreferences)
        return activity?.let { RecentItemAdapter(it.applicationContext, favouriteMenuItems) }
    }
}