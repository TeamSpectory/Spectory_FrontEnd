package com.example.spectory


import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthRetrofitInterface {
    @POST("/user/join")
    fun signUp(@Body user: UserData): Call<AuthResponse>
}