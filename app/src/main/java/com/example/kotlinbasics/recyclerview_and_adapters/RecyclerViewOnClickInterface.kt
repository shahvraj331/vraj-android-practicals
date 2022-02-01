package com.example.kotlinbasics.recyclerview_and_adapters

interface RecyclerViewOnClickInterface {
    fun onItemClick(position: Int, isChecked: Boolean)
    fun onLongClick(position: Int)
}