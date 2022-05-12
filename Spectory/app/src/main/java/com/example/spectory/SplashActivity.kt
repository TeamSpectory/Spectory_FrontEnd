package com.example.spectory

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.spectory.databinding.ActivityLoginBinding
import com.example.spectory.databinding.ActivitySplashBinding

class SplashActivity: AppCompatActivity() {

    lateinit var binding:ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var intent = Intent(this, LoginActivity::class.java)
        //2초 뒤 LoginActivity로 이동
        Handler().postDelayed({startActivity(intent)}, 2000L)
    }
}