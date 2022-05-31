package com.example.spectory

import com.google.gson.annotations.SerializedName


data class UserData(
    @SerializedName(value="id") var id : String,
    @SerializedName(value="pw")var pw : String,
    @SerializedName(value="nickname")var nickname : String
)
