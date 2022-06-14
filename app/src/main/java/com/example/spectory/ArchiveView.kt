package com.example.spectory

interface ArchiveView {
    fun onArchiveSuccess(resp : List<PostResponse>)
    fun onArchiveFailure()
}