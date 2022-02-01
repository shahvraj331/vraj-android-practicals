package com.example.kotlinbasics.recyclerview_and_adapters.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.kotlinbasics.commonFolder.utils.Constants.ONE
import com.example.kotlinbasics.commonFolder.utils.Constants.THREE
import com.example.kotlinbasics.commonFolder.utils.Constants.TWO
import com.example.kotlinbasics.commonFolder.utils.Constants.ZERO
import com.example.kotlinbasics.recyclerview_and_adapters.fragment.HomeFragment
import com.example.kotlinbasics.recyclerview_and_adapters.fragment.MyProfileFragment
import com.example.kotlinbasics.recyclerview_and_adapters.fragment.SearchFragment

class BottomNavAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return THREE
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            ZERO -> HomeFragment()
            ONE -> SearchFragment()
            TWO -> MyProfileFragment()
            else -> HomeFragment()
        }
    }
}