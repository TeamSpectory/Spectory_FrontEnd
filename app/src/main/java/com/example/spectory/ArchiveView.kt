package com.example.spectory

interface ArchiveView {
    fun onArchiveSuccess(resp : PostResponse)
    fun onArchiveFailure()
}