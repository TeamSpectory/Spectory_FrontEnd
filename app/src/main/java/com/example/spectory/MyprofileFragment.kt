package com.example.spectory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        return binding.root
    }
}