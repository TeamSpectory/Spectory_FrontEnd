package com.example.spectory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.spectory.databinding.FragmentArchivingBinding
import com.example.spectory.databinding.FragmentSettingBinding

class SettingFragment : Fragment(), ProfileView {
    lateinit var binding: FragmentSettingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingBinding.inflate(inflater, container, false)
        myprofile()

        binding.settingOkBtn.setOnClickListener {
            //수정 완료 후 서버에 post 보내는 코드 짜야 함
            changeMyProfileFragment()
        }

        return binding.root
    }

    private fun changeMyProfileFragment(){
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, MyprofileFragment()).commit()
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
                binding.settingName.hint = data.nickname
                binding.settingId.hint = data.id
                binding.settingDate.hint = data.created_date
            }
        }
    }

    override fun onProfileFailure() {

    }

}