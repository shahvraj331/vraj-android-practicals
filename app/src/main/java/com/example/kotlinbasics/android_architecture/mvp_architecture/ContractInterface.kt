package com.example.kotlinbasics.android_architecture.mvp_architecture

interface View {
    fun initializeView()
    fun updateView(valueIncreased: Boolean)
}

interface Presenter {
    fun getCounter(): String
    fun incrementCounter()
    fun decrementCounter()
}

interface Model {
    fun getCounter(): String
    fun incrementCounter()
    fun decrementCounter()
}