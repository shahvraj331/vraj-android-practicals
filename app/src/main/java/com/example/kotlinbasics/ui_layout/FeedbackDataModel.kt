package com.example.kotlinbasics.ui_layout

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FeedbackDataModel(
    val name: String, val email: String, val feedbackType: String?,
    val detailedFeedback: String, val getEmailResponse: Boolean): Parcelable
