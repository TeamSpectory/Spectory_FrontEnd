package com.example.spectory

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.spectory.databinding.ActivityLoginBinding
import com.example.spectory.databinding.ActivityMainBinding

class LoginActivity: AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        //회원가입하기 버튼 클릭시 회원가입 Activity로 이동
        binding.loginGoBtn.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
            finish()
        }
        //로그인 완료 버튼 클릭시 main activity로 이동
        binding.loginOkBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        setContentView(binding.root)
    }
}