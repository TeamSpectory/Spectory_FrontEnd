package com.example.spectory

import com.google.gson.annotations.SerializedName
import java.io.File

data class PostResponse(
    @SerializedName(value="status") val status:Int,
    @SerializedName(value="message") val message: String,
    @SerializedName(value="data") val data: List<PostList>
)

data class PostList(
    @SerializedName(value="postIdx") val postIdx: Int,
    @SerializedName(value="type") val type: Int,
    @SerializedName(value="title") val title: String,
    @SerializedName(value="startDate") val startDate: String,
    @SerializedName(value="endDate") val endDate: String,
    @SerializedName(value="picture") val picture: String,
    @SerializedName(value="rates") val rates: Int,
    @SerializedName(value="tags") val tags: String
    )
