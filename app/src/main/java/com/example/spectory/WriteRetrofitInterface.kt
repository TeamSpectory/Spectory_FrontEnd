package com.example.spectory

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface WriteRetrofitInterface {
    @POST("/post/write")
    fun write(@Body write: WriteData): Call<AuthResponse>
}