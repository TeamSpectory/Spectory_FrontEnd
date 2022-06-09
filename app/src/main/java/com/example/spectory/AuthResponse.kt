package com.example.spectory

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName(value="status") val status:Int,
    @SerializedName(value="message") val message: String,
    @SerializedName(value="data") val data: Data
    )

data class Data(
    @SerializedName(value="userIdx") var userIdx : Int,
    @SerializedName(value="token") var token: String?
)