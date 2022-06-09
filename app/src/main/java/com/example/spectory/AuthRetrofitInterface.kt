package com.example.spectory


import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthRetrofitInterface {
    //회원가입
    @POST("/user/join")
    fun signUp(@Body user: UserData): Call<AuthResponse>

    //로그인
    @POST("/user/login")
    fun login(@Body user: UserData): Call<AuthResponse>
}