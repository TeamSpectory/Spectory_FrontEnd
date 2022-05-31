package com.example.spectory

data class AuthResponse(
    val status:Int,
    val message: String,
    val data: Data?
    )

data class Data(
    var userIdx : Int
)