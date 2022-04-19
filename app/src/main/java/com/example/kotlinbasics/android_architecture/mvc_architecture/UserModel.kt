package com.example.kotlinbasics.android_architecture.mvc_architecture

data class UserModel(val username: String, val password: String) {

    fun checkUserValidity(username: String, password: String): Int {
        return if (username.trim().isEmpty() || password.trim().isEmpty()) {
            -1
        } else {
            0
        }
    }
}