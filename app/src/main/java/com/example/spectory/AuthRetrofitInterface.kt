package com.example.spectory


import okhttp3.MultipartBody
import okhttp3.RequestBody
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
    @HTTP(method = "DELETE",hasBody = true,path = "/user/delete/{userIdx}")
    fun deleteUser(@Path("userIdx") userIdx: Int, @Body token: TokenData): Call<AuthResponse>

    //로그인
    @POST("/user/login")
    fun login(@Body user: UserData): Call<AuthResponse>

    //게시글 쓰기
//    @Headers("Content-Type:application/json")
    @Multipart
    @POST("/post/write")
    fun writeImage(
        @Part type:  MultipartBody.Part,
        @Part title:  MultipartBody.Part,
        @Part startDate:  MultipartBody.Part,
        @Part endDate:  MultipartBody.Part,
        @Part situation:  MultipartBody.Part,
        @Part action:  MultipartBody.Part,
        @Part learned:  MultipartBody.Part,
        @Part rates:  MultipartBody.Part,
        @Part tags:  MultipartBody.Part,
        @Part userIdx:  MultipartBody.Part,
        @Part image:  MultipartBody.Part
    ): Call<WriteResponse>

    //게시글 삭제
    @Headers("Content-Type:application/json")
    @HTTP(method = "DELETE",hasBody = true,path = "/post/delete/{postIdx}")
    fun deletePost(@Path("postIdx") postIdx: Int, @Body token: TokenData): Call<AuthResponse>

    //전체 글 보기
    @Headers("Content-Type:application/json")
    @GET("/post/list/{userIdx}")
    fun archiving(@Path("userIdx") userIdx: Int): Call<PostResponse>


    //내 프로필
    @Headers("Content-Type:application/json")
    @GET("/user/profile/{userIdx}")
    fun myprofile(@Path("userIdx") userIdx: Int): Call<AuthResponse>
}