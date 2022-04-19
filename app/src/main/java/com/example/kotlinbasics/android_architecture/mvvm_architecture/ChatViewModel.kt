package com.example.kotlinbasics.android_architecture.mvvm_architecture

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChatViewModel: ViewModel() {

    val latestMessageFromFirst = MutableLiveData<String>()
    private val emailOfUser = MutableLiveData<String>()
    val isEmailValid = MutableLiveData<Boolean>()
    val emailVerifyImageUrl = MutableLiveData<String>()

    fun setEmailOfUser(email: String) {
        emailOfUser.value = email
    }

    fun setLatestMessageFromFirst(data: String) {
        latestMessageFromFirst.value = data
    }

    fun verifyEmailAddress() {
        emailOfUser.value?.let { email ->
            isEmailValid.value = email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }
    }
}