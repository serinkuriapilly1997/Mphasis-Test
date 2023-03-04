package com.app.mymainapp.models

import com.google.gson.annotations.SerializedName

data class TestApiNestedModel (
    @SerializedName("body")
    val body: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("userId")
    val userId: Int?,
    val Username:ArrayList<Name>?,

    var isVisible:Boolean?=false
)

data class Name(
    var userName:String?
)