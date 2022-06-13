package com.example.spectory

import com.google.gson.annotations.SerializedName

data class WriteData(
    @SerializedName(value = "type") var type: Int,
    @SerializedName(value = "title") var title: String,
    @SerializedName(value = "startDate") var startDate: String,
    @SerializedName(value = "endDate") var endDate: String,
    @SerializedName(value = "situation") var situation: String,
    @SerializedName(value = "action") var action: String,
    @SerializedName(value = "learned") var learned: String,
    @SerializedName(value = "rates") var rates: Int,
    @SerializedName(value = "tags") var tags: String,
    @SerializedName(value = "userIdx") var userIdx: Int
)
