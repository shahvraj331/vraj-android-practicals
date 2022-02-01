package com.example.kotlinbasics.ui_widgets

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EmployeeDetailsModel(
    val name: String, val gender: String, val email: String, val phone: String,
    val isInterned: Boolean, val description: String, val priority: Boolean, val skillsArray: MutableList<String>) : Parcelable
