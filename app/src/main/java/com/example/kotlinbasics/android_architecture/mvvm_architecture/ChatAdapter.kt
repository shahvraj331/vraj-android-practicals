package com.example.kotlinbasics.android_architecture.mvvm_architecture

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.kotlinbasics.commonFolder.utils.Constants

class ChatAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return Constants.TWO
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            Constants.ZERO -> UserOneFragment()
            Constants.ONE -> UserTwoFragment()
            else -> UserOneFragment()
        }
    }
}