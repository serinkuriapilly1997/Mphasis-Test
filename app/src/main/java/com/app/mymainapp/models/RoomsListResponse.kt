package com.app.mymainapp.models


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class RoomsListResponse(
    @SerializedName("createdAt")
    val createdAt: String?="",
    @SerializedName("isOccupied")
    val isOccupied: Boolean?=false,
    @SerializedName("maxOccupancy")
    val maxOccupancy: Int?=null,
    @SerializedName("id")
    val id: String?=""
)
