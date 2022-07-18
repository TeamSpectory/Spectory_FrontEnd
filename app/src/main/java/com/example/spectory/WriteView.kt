package com.example.spectory

interface WriteView {
    fun onWriteSuccess(status : Int)
    fun onWriteFailure()
}