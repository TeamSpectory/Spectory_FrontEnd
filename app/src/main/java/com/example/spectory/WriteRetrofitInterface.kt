package com.example.spectory

import retrofit2.Call
import retrofit2.http.*

interface WriteRetrofitInterface {
    @POST("/post/write")
    fun write(@Body write: WriteData): Call<WriteResponse>

    //글 상세정보 보기
    @Headers("Content-Type:application/json")
    @GET("/post/detail/{postIdx}")
    fun detail(@Path("postIdx") postIdx: Int): Call<DetailResponse>
}