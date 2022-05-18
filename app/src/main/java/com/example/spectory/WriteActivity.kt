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

        //activity 종료
        binding.writeCancelBtn.setOnClickListener {
            closeWriteActivity()
        }
        binding.writeCloseBtn.setOnClickListener {
            closeWriteActivity()
        }
        binding.writeOkBtn.setOnClickListener {
            closeWriteActivity()
        }

        setContentView(binding.root)
    }

    private fun closeWriteActivity(){
        finish()
    }
}