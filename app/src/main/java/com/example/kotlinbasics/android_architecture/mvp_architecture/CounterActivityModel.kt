package com.example.kotlinbasics.android_architecture.mvp_architecture

class CounterActivityModel: Model {

    private var mCounter = 0

    override fun getCounter(): String = mCounter.toString()

    override fun incrementCounter() {
        mCounter++
    }

    override fun decrementCounter() {
        mCounter--
    }
}