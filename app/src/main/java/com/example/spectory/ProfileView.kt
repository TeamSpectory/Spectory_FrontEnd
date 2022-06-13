package com.example.spectory

interface ProfileView {
    fun onProfileSuccess(status : Int, data: Data)
    fun onProfileFailure()
}