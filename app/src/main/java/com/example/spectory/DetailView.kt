package com.example.spectory

interface DetailView {
    fun onDetailSuccess(status: Int, resp : DetailResponse)
    fun onDetailFailure()
}