package com.example.spectory

import com.google.gson.annotations.SerializedName


data class TokenData(
    @SerializedName(value="token") var token : String
)
