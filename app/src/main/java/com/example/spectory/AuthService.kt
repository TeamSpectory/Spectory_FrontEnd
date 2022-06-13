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
    private lateinit var profileView: ProfileView
    private lateinit var writeView: WriteView


    fun setSignUpView(signUpView: SignUpView){
        this.signUpView = signUpView
    }

    fun setLoginView(loginView: LoginView){
        this.loginView = loginView
    }

    fun setProfileView(profileView: ProfileView){
        this.profileView = profileView
    }

    fun setWriteView(writeView: WriteView){
        this.writeView = writeView
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

    //내 프로필 확인
    fun myprofile(userIdx: Int){
        val authService = getRetrofit().create(AuthRetrofitInterface::class.java)
        authService.myprofile(userIdx).enqueue(object: Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                Log.d("PROFILE", response.toString())
                val resp: AuthResponse = response.body()!!
                when(val status = resp.status){
                    200 -> profileView.onProfileSuccess(status,resp.data!!)
                    else -> profileView.onProfileFailure()
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Log.d("PROFILE/FAILURE",t.message.toString())
            }

        } )
    }

    //게시글 쓰기
    fun write(post: WriteData){
        val authService = getRetrofit().create(AuthRetrofitInterface::class.java)
        authService.write(post).enqueue(object: Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                Log.d("WRITE", response.toString())
//                val resp: AuthResponse = response.body()!!
//                when(val status = resp.status){
//                    200 -> writeView.onWriteSuccess(status,resp.data!!)
//                    else -> writeView.onWriteFailure()
//                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Log.d("WRITE/FAILURE",t.message.toString())
            }

        } )

        Log.d("WRITE/FAILURE","HELLO")
    }
}