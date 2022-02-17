package com.example.kotlinbasics.activity_intent_fragment

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FragmentViewModel: ViewModel() {

    private val currentImage: MutableLiveData<Uri> = MutableLiveData<Uri>()
    private val conversationArray = MutableLiveData<MutableList<String>>()
    private val isJetPackEnabled = MutableLiveData<Boolean>()

    fun updateCurrentImage(newImageUri: Uri) {
        currentImage.value = newImageUri
    }

    fun retrieveCurrentImage(): Uri? {
        return currentImage.value
    }

    fun updateConversation(newMessage: String) {
        if (conversationArray.value == null) {
            conversationArray.value = mutableListOf()
        }
        conversationArray.value?.add(newMessage)
    }

    fun retrieveConversation(): MutableList<String> {
        conversationArray.value?.let {
            return it
        }
        return mutableListOf()
    }

    fun setIsJetPackEnabled(newValue: Boolean) {
        isJetPackEnabled.value = newValue
    }

    fun getIsJetPackEnabled(): Boolean {
        isJetPackEnabled.value?.let {
            return it
        }
        return false
    }
}