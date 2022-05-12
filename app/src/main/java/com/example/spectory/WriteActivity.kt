package com.example.spectory

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.spectory.databinding.ActivityLoginBinding
import com.example.spectory.databinding.ActivityWriteBinding

class WriteActivity: AppCompatActivity() {

    lateinit var binding: ActivityWriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}