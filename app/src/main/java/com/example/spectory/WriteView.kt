package com.example.spectory

interface WriteView {
    fun onWriteSuccess(status : Int, message : String, data: Data)
    fun onWriteFailure()
}