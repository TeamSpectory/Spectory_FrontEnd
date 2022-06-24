package com.example.spectory


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.spectory.databinding.FragmentMyprofileBinding

class MyprofileFragment: Fragment(), ProfileView, DeleteUserView {
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

        binding.myprofileMoreBtn.setOnClickListener {
            //drawer 열기
            var popup = PopupMenu(this.context, it)
            var menuInflater = MenuInflater(this.context)
            menuInflater?.inflate(R.menu.profile_menu,popup.menu)
            popup.show()
            popup.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.reset -> {
                        //내 정보 초기화
                        true
                    }
                    R.id.delete -> {
                        //회원 탈퇴
                        deleteUser()
                        true
                    } else -> {
                        false
                    }
                }
            }


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

    private fun getToken(): TokenData{
        val spf = this.activity?.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)

        val tokenData = TokenData("")
        tokenData.token = spf!!.getString("jwt","")!!
        Log.d("TOKEN",tokenData.token)
        return tokenData
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

    private fun deleteUser() {
        val authService = AuthService()
        authService.setDeleteUserView(this)

        authService.deleteUser(getUserIdx(),getToken())
    }


    override fun onDeleteSuccess(status: Int) {
        when(status){
            200 -> {
                Toast.makeText(this.context,"탈퇴 되었습니다",Toast.LENGTH_SHORT).show()

                val intent = Intent(context, LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onDeleteFailure() {
        TODO("Not yet implemented")
    }


}