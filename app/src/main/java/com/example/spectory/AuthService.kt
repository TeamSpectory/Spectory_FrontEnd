package com.example.spectory

import android.util.Log
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import kotlin.math.log
import kotlin.math.sign

class AuthService {
    private lateinit var signUpView: SignUpView
    private lateinit var loginView: LoginView
    private lateinit var profileView: ProfileView
    private lateinit var writeView: WriteView
    private lateinit var archiveView: ArchiveView
    private lateinit var detailView: DetailView
    private lateinit var deleteUserView: DeleteUserView
    private lateinit var deletePostView: DeletePostView

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

    fun setArchiveView(archiveView: ArchiveView){
        this.archiveView = archiveView
    }

    fun setDetailView(detailView: DetailView){
        this.detailView = detailView
    }

    fun setDeleteUserView(deleteUserView: DeleteUserView){
        this.deleteUserView = deleteUserView
    }

    fun setDeletePostView(deletePostView: DeletePostView){
        this.deletePostView = deletePostView
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
    fun writeImage(type: String,title: String, startDate: String,endDate: String,situation:String,action:String,learned:String,rates:Int,tags:String,userIdx:Int,image: File){
        val authService = getRetrofit().create(AuthRetrofitInterface::class.java)
        authService.writeImage(
            FormDataUtil.getBody("type", type),
            FormDataUtil.getBody("title",title),
            FormDataUtil.getBody("startDate",startDate),
            FormDataUtil.getBody("endDate",endDate),
            FormDataUtil.getBody("situation",situation),
            FormDataUtil.getBody("action",action),
            FormDataUtil.getBody("learned",learned),
            FormDataUtil.getBody("rates",rates),
            FormDataUtil.getBody("tags",tags),
            FormDataUtil.getBody("userIdx",userIdx),
            FormDataUtil.getImageBody("image",image)
        ).enqueue(object: Callback<WriteResponse> {
            override fun onResponse(call: Call<WriteResponse>, response: Response<WriteResponse>) {
                Log.d("WRITE", response.toString())
                Log.d("WRITE", response.errorBody().toString())
                val resp: WriteResponse = response.body()!!
                when(val status = resp.status){
                    200 -> writeView.onWriteSuccess(status)
                    else -> writeView.onWriteFailure()
                }
            }

            override fun onFailure(call: Call<WriteResponse>, t: Throwable) {
                Log.d("WRITE/FAILURE",t.message.toString())
            }

        } )

        Log.d("WRITE/FAILURE","HELLO")
    }


    //전체 글 보기
    fun archiving(userIdx: Int){
        val authService = getRetrofit().create(AuthRetrofitInterface::class.java)
        authService.archiving(userIdx).enqueue(object: Callback<PostResponse> {
            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                Log.d("ARCHIVE", response.toString())
                val resp: PostResponse = response.body()!!
                archiveView.onArchiveSuccess(resp)
                Log.d("아아아악",resp.toString())
            }

            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                Log.d("ARCHIVE/FAILURE",t.message.toString())
            }

        } )
    }

    //글 상세정보 보기
    fun detail(postIdx: Int){
        val authService = getRetrofit().create(WriteRetrofitInterface::class.java)
        authService.detail(postIdx).enqueue(object: Callback<DetailResponse> {
            override fun onResponse(call: Call<DetailResponse>, response: Response<DetailResponse>) {
                Log.d("DETAIL", response.toString())
                val resp: DetailResponse = response.body()!!
                when(val status = resp.status){
                    200 -> detailView.onDetailSuccess(status,resp)
                    else -> detailView.onDetailFailure()
                }
            }

            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                Log.d("DETAIL/FAILURE",t.message.toString())
            }

        } )
    }

    //회원탈퇴
    fun deleteUser(userIdx: Int,token:TokenData){
        val authService = getRetrofit().create(AuthRetrofitInterface::class.java)
        authService.deleteUser(userIdx,token).enqueue(object: Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                Log.d("DELETE_USER", response.toString())
                val resp: AuthResponse = response.body()!!
                when(val status = resp.status){
                    200 -> deleteUserView.onDeleteSuccess(status)
                    else -> deleteUserView.onDeleteFailure()
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Log.d("DELETE_USER/FAILURE",t.message.toString())
            }

        } )
    }

    //게시글 삭제
    fun deletePost(postIdx: Int, token: TokenData){
        val authService = getRetrofit().create(AuthRetrofitInterface::class.java)
        authService.deletePost(postIdx,token).enqueue(object: Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                Log.d("DELETE_POST", response.toString())
                val resp: AuthResponse = response.body()!!
                when(val status = resp.status){
                    200 -> deletePostView.onDeletePostSuccess(status)
                    else -> deletePostView.onDeletePostFailure()
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Log.d("DELETE_POST/FAILURE",t.message.toString())
            }

        } )
    }

}