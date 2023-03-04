package com.app.mymainapp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable


@Parcelize
@Serializable
data class PeopleData(
    @SerializedName("title")
    val title: String?,
    @SerializedName("body")
    val body: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("toId")
    val toId: String?,
    @SerializedName("fromId")
    val fromId: String?,
    @SerializedName("meetingid")
    val meetingid: String?
):Parcelable