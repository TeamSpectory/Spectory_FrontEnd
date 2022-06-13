package com.example.spectory

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.spectory.databinding.FragmentMyprofileBinding

class MyprofileFragment: Fragment(), ProfileView {
    lateinit var binding: FragmentMyprofileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentMyprofileBinding.inflate(inflater, container, false)

        myprofile()

        //연필 모양 클릭 시 내정보 수정 화면 열기 (SettingFragment)
        binding.myprofileEditBtn.setOnClickListener {
            changeSettingFragment()
        }
        return binding.root
    }


    private fun changeSettingFragment(){
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, SettingFragment()).commit()
    }

    private fun getUserIdx(): Int{
        val spf = this.activity?.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)

        return spf!!.getInt("userIdx",0)
    }

    private fun myprofile() {
        val authService = AuthService()
        authService.setProfileView(this)

        //userIdx를 넣어줘야함 !! -> jwt 사용하기
        authService.myprofile(getUserIdx())
    }

    //data에 있는 값들로 text 바꾸기
    override fun onProfileSuccess(status: Int, data: Data) {
        when(status){
            200 -> {
                binding.myprofileName.text = data.nickname
                binding.myprofileId.text = data.id
                binding.myprofileDate.text = data.created_date
            }
        }
    }

    override fun onProfileFailure() {

    }
}