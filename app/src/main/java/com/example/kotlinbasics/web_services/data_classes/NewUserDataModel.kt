package com.example.kotlinbasics.web_services.data_classes

import com.google.gson.annotations.SerializedName

data class NewUserDataModel(
    @SerializedName("name")
    val nameOfUser: String = "",
    @SerializedName("job")
    val jobOfUser: String = ""
)

data class NewUserResponseModel(
    @SerializedName("name")
    val nameOfUser: String = "",
    @SerializedName("job")
    val jobOfUser: String = "",
    @SerializedName("id")
    val idOfUser: String = "",
    @SerializedName("createdAt")
    val createdAt: String = ""
)
