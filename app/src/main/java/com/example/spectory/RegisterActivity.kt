package com.example.spectory

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.spectory.databinding.ActivityLoginBinding
import com.example.spectory.databinding.ActivityRegisterBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class RegisterActivity:AppCompatActivity() {

    lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)

        //회원가입 완료 버튼 클릭시 main activity로 이동
        binding.registerOkBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            signUp()
            //finish()
        }

        setContentView(binding.root)

    }

    //입력한 값들로부터 UserData 객체 받아오기
    private fun getUserData(): UserData {
        val id: String = binding.registerIdEt.text.toString()
        val pw: String = binding.registerEtPassword.text.toString()
        val nickname: String = binding.registerEtNickname.text.toString()

        return UserData(id,pw,nickname)
    }

    private fun signUp() {
        //비밀번호 입력과 재입력이 일치하지 않는 경우
        if(binding.registerEtPassword.text.toString() != binding.registerEtPasswordConfirm.text.toString()){
            Toast.makeText(this, "비밀번호가 일치하지 않습니다.",Toast.LENGTH_SHORT).show()
            return
        }

        val authService = getRetrofit().create(AuthRetrofitInterface::class.java)
        authService.signUp(getUserData()).enqueue(object: Callback<AuthResponse>{
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                Log.d("SIGNUP/SUCCESS", response.toString())
                val resp: AuthResponse = response.body()!!
                when(resp.status){
                    200->Log.d("SIGNUP/SUCCESS", resp.message)
                    400->Log.d("SIGNUP/FAILURE", resp.message)
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Log.d("SIGNUP/FAILURE",t.message.toString())
            }

        } )

        Log.d("SIGNUP/FAILURE","HELLO")
    }
}