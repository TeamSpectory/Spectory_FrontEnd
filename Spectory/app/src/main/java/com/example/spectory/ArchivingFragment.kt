package com.example.spectory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.spectory.databinding.FragmentArchivingBinding

class ArchivingFragment: Fragment() {
    lateinit var binding : FragmentArchivingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArchivingBinding.inflate(inflater,container,false)

        return binding.root
    }
}