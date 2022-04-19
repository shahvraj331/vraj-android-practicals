package com.example.kotlinbasics.web_services.interfaces

interface ApiCallbackListener {
    fun<T: Any> onSuccess(data: T)
    fun onFailure()
}