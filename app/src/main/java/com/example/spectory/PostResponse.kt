package com.example.spectory

import com.google.gson.annotations.SerializedName

data class PostResponse(
    @SerializedName(value="type") val type: Int,
    @SerializedName(value="title") val title: String,
    @SerializedName(value="startDate") val startDate: String,
    @SerializedName(value="endDate") val endDate: String,
    @SerializedName(value="picture") val picture: String,
    @SerializedName(value="rates") val rates: Int,
    @SerializedName(value="tags") val tags: String
    )
