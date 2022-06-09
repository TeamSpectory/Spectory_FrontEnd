package com.example.spectory

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.spectory.databinding.ActivityLoginBinding
import com.example.spectory.databinding.ActivityMainBinding

class LoginActivity: AppCompatActivity(), LoginView {

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
//            startActivity(Intent(this, MainActivity::class.java))
//            finish()
            login()

        }

        setContentView(binding.root)
    }

    //입력한 값들로부터 UserData 객체 받아오기
    private fun getUserData(): UserData {
        val id: String = binding.loginIdEt.text.toString()
        val pw: String = binding.loginPwdEt.text.toString()
        val nickname: String = ""

        return UserData(id,pw,nickname)
    }

    private fun login() {
        val authService = AuthService()
        authService.setLoginView(this)

        authService.login(getUserData())

    }

    override fun onLoginSuccess(status: Int, data: Data) {
        when(status){
            200 -> {
                saveJwt(data.token!!)
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }

    override fun onLoginFailure() {
        TODO("Not yet implemented")
    }

    //로그인 할 때 token 받아와서 저장 -> userIdx도 저장?
    private fun saveJwt(jwt: String){
        val spf = getSharedPreferences("auth", MODE_PRIVATE)
        val editor = spf.edit()

        editor.putString("jwt",jwt)
        editor.apply()

    }
}