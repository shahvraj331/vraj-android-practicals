package com.example.kotlinbasics.android_architecture.mvc_architecture

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers

class LoginApi(username: String, password: String, loginResultInterface: LoginResultInterface) {

    var userModel = UserModel(username, password)
    private val userName: String = username
    private val userPassword: String = password
    var mLoginResultInterface: LoginResultInterface = loginResultInterface

    fun doLogin() {
        val code = userModel.checkUserValidity(userName, userPassword)
        val result = code == 0
        GlobalScope.launch {
            delay(3000)
            withContext(Dispatchers.Main) {
                mLoginResultInterface.onLoginResult(result)
            }
        }
    }
}