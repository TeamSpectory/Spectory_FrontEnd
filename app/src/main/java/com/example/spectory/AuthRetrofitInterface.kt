package com.example.spectory


import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface AuthRetrofitInterface {
    //회원가입
    @POST("/user/join")
    fun signUp(@Body user: UserData): Call<AuthResponse>

    //회원탈퇴
    @Headers("Content-Type:application/json")
//    @DELETE("/user/delete/{userIdx}")
//    fun deleteUser(@Path("userIdx") userIdx: Int, @Query("token") token: String): Call<AuthResponse>
    //@FormUrlEncoded
    @HTTP(method = "DELETE",hasBody = true,path = "/user/delete/{userIdx}")
    fun deleteUser(@Path("userIdx") userIdx: Int, @Body token: TokenData): Call<AuthResponse>

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