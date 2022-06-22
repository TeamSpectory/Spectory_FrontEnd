package com.example.spectory

import com.google.gson.annotations.SerializedName

data class DetailResponse(
    @SerializedName(value="status") val status:Int,
    @SerializedName(value="message") val message: String,
    @SerializedName(value="data") val detailData: DetailData
    )

data class DetailData(
    @SerializedName(value="title") val title: String,
    @SerializedName(value="startDate") val startDate: String,
    @SerializedName(value="endDate") val endDate: String,
    @SerializedName(value="situation") val situation: String,
    @SerializedName(value="action") val action: String,
    @SerializedName(value="learned") val learned: String,
    @SerializedName(value="picture") val picture: String,
    @SerializedName(value="rates") val rates: Int,
    @SerializedName(value="tags") val tags: String
    )
