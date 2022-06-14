package com.example.spectory

import com.google.gson.annotations.SerializedName

data class WriteResponse(
    @SerializedName(value="status") val status:Int,
    @SerializedName(value="message") val message: String,
    @SerializedName(value="data") val data: String
)
