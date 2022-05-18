package com.example.spectory

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.spectory.databinding.FragmentMyprofileBinding

class MyprofileFragment: Fragment() {
    lateinit var binding: FragmentMyprofileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentMyprofileBinding.inflate(inflater, container, false)

        //연필 모양 클릭 시 내정보 수정 화면 열기 (SettingFragment)
        binding.myprofileEditBtn.setOnClickListener {
            Toast.makeText(this.context,"클릭됨",Toast.LENGTH_SHORT).show()
            changeSettingFragment()
        }
        return binding.root
    }


    private fun changeSettingFragment(){
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, SettingFragment()).commit()
    }
}