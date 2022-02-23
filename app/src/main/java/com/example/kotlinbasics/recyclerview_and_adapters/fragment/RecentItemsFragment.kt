package com.example.kotlinbasics.recyclerview_and_adapters.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ListView
import android.widget.TextView
import com.example.kotlinbasics.R
import com.example.kotlinbasics.commonFolder.utils.Constants.MENU_SHARED_PREF_KEY
import com.example.kotlinbasics.recyclerview_and_adapters.MenuItems
import com.example.kotlinbasics.recyclerview_and_adapters.adapters.RecentItemAdapter

class RecentItemsFragment : Fragment() {

    private var sharedPreferences: SharedPreferences? = null
    private lateinit var recentMenuItems: MutableList<MenuItems>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        sharedPreferences = activity?.getSharedPreferences(MENU_SHARED_PREF_KEY, Context.MODE_PRIVATE)
        val recentList = view.findViewById<ListView>(R.id.lvRecent)
        val emptyListText = view.findViewById<TextView>(R.id.tvEmptyListText)
        recentMenuItems =
            MenuItems.retrieveRecentItems(activity?.applicationContext, sharedPreferences)

        val adapter = activity?.let { RecentItemAdapter(it.applicationContext, recentMenuItems) }
        recentList.adapter = adapter
        recentList.emptyView = emptyListText

        return view
    }
}