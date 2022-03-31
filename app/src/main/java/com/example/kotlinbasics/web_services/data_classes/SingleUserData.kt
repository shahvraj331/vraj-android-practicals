package com.example.kotlinbasics.web_services.data_classes

import com.google.gson.annotations.SerializedName

data class SingleUserData(
    @SerializedName("data")
    val data: UsersList = UsersList(),
    @SerializedName("support")
    val support: Support = Support()
)
