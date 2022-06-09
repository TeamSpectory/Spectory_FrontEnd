package com.example.spectory

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log
import kotlin.math.sign

class AuthService {
    private lateinit var signUpView: SignUpView
    private lateinit var loginView: LoginView

    fun setSignUpView(signUpView: SignUpView){
        this.signUpView = signUpView
    }

    fun setLoginView(loginView: LoginView){
        this.loginView = loginView
    }


    //회원가입
    fun signUp(user: UserData){
        val authService = getRetrofit().create(AuthRetrofitInterface::class.java)
        authService.signUp(user).enqueue(object: Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                Log.d("SIGNUP/SUCCESS", response.toString())
                val resp: AuthResponse = response.body()!!
                when(resp.status){
                    200-> signUpView.onSignUpSuccess()
                    400 -> signUpView.onSignUpFailure()
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Log.d("SIGNUP/FAILURE",t.message.toString())
            }

        } )

        Log.d("SIGNUP/FAILURE","HELLO")
    }

    //로그인
    fun login(user: UserData){
        val authService = getRetrofit().create(AuthRetrofitInterface::class.java)
        authService.login(user).enqueue(object: Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                Log.d("LOGIN/SUCCESS", response.toString())
                val resp: AuthResponse = response.body()!!
                when(val status = resp.status){
                    200 -> loginView.onLoginSuccess(status,resp.data!!)
                    else -> loginView.onLoginFailure()
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Log.d("LOGIN/FAILURE",t.message.toString())
            }

        } )

        //Log.d("LOGIN/FAILURE","HELLO")
    }

}