package com.example.spectory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.spectory.databinding.FragmentArchivingBinding
import com.example.spectory.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {
    lateinit var binding: FragmentSettingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingBinding.inflate(inflater, container, false)

        binding.settingOkBtn.setOnClickListener {
            changeMyProfileFragment()
        }

        return binding.root
    }

    private fun changeMyProfileFragment(){
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, MyprofileFragment()).commit()
    }

}