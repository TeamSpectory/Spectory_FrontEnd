package com.example.spectory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spectory.databinding.FragmentArchivingBinding

class ArchivingFragment: Fragment() {

    lateinit var binding : FragmentArchivingBinding
    private var archiveDatas = ArrayList<ArchiveData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArchivingBinding.inflate(inflater,container,false)

        archiveDatas.apply {
            add(ArchiveData("22.01.23~22.01.26",R.drawable.profile,"5.0","OO 공모전","직무역량","성격 및 가치관","협업 경험","동아리","앱 프로젝트","주도적 참여"))
            add(ArchiveData("22.01.23~22.01.26", R.drawable.ic_launcher_background,"3.0","공모전 3","직무역량","성격 및 가치관","협업 경험","웹 프로젝트","동아리","디자인"))
            add(ArchiveData("22.01.23~22.01.26",
                R.drawable.home,"3.0","프로젝트","직무역량","성격 및 가치관","협업 경험","동아리","앱 프로젝트","주도적 참여"))
            add(ArchiveData("22.01.23~22.01.26",
                R.drawable.ic_bookmark,"1.0","스펙토리","직무역량","성격 및 가치관","협업 경험","동아리","앱 프로젝트","주도적 참여"))
            add(ArchiveData("22.01.23~22.01.26",R.drawable.profile,"3.0","얍","직무역량","성격 및 가치관","협업 경험","동아리","앱 프로젝트","주도적 참여"))
            add(ArchiveData("22.01.23~22.01.26",R.drawable.profile,"5.0","히히 공모전","직무역량","성격 및 가치관","협업 경험","동아리","앱 프로젝트","주도적 참여"))
        }

        val archiveAdapter = ArchiveAdapter(archiveDatas)
        binding.archiveRv.adapter = archiveAdapter
        binding.archiveRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        return binding.root
    }
}