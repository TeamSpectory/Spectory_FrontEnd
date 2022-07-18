package com.example.spectory

import com.google.gson.annotations.SerializedName
import retrofit2.http.Part
import java.io.File
import java.util.*

data class WriteData(
    @Part(value = "type") var type: Int,
    @Part(value = "title") var title: String,
    @Part(value = "startDate") var startDate: String,
    @Part(value = "endDate") var endDate: String,
    @Part(value = "situation") var situation: String,
    @Part(value = "action") var action: String,
    @Part(value = "learned") var learned: String,
    @Part(value = "rates") var rates: Int,
    @Part(value = "tags") var tags: String,
    @Part(value = "userIdx") var userIdx: Int,
    @Part(value = "userIdx") var image: File
)
