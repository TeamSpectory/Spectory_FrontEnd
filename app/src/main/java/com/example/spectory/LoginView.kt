package com.example.spectory

interface LoginView {
    fun onLoginSuccess(status : Int, data: Data)
    fun onLoginFailure()
}