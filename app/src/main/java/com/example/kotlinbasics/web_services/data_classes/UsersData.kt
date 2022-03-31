package com.example.kotlinbasics.web_services.data_classes

import com.google.gson.annotations.SerializedName

data class UsersData(
    @SerializedName("page")
    var page: Int? = null,
    @SerializedName("per_page")
    var perPage: Int? = null,
    @SerializedName("total")
    var total: Int? = null,
    @SerializedName("total_pages")
    var totalPages: Int? = null,
    @SerializedName("data")
    var usersList: ArrayList<UsersList> = arrayListOf(),
    @SerializedName("support")
    var support: Support? = Support()
)

data class UsersList(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("email")
    var email: String? = null,
    @SerializedName("first_name")
    var firstName: String? = null,
    @SerializedName("last_name")
    var lastName: String? = null,
    @SerializedName("avatar")
    var avatar: String? = null
)

data class Support(
    @SerializedName("url")
    var url: String? = null,
    @SerializedName("text")
    var text: String? = null
)
