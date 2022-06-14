package com.example.spectory


import retrofit2.Call
import retrofit2.http.*

interface AuthRetrofitInterface {
    //회원가입
    @POST("/user/join")
    fun signUp(@Body user: UserData): Call<AuthResponse>

    //로그인
    @POST("/user/login")
    fun login(@Body user: UserData): Call<AuthResponse>

    //게시글 쓰기
    @Headers("Content-Type:application/json")
    @POST("/post/write")
    fun write(@Body user: WriteData): Call<WriteResponse>

    //전체 글 보기
    @Headers("Content-Type:application/json")
    @GET("/post/list/{userIdx}")
    fun archiving(@Path("userIdx") userIdx: Int): Call<List<PostResponse>>


    //내 프로필
    @Headers("Content-Type:application/json")
    @GET("/user/profile/{userIdx}")
    fun myprofile(@Path("userIdx") userIdx: Int): Call<AuthResponse>

}