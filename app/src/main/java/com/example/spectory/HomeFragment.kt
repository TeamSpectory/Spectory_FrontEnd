package com.example.spectory

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spectory.databinding.FragmentArchivingBinding
import com.example.spectory.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    private var jobDatas = ArrayList<HomeData>()
    private var togetherDatas = ArrayList<HomeData>()
    private var personDatas = ArrayList<HomeData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container,false)

        //+버튼 누르면 write activity로 이동
        binding.homeGoToWrite.setOnClickListener {
            val intent = Intent(context, WriteActivity::class.java)
            startActivity(intent)
        }


        jobDatas.apply {
            add(HomeData("2022.02.06","직무",R.drawable.profile,"#박람회 #취업 #참여"))
            add(HomeData("2022.03.08","역량", R.drawable.home,"#직무 #역량 #굿"))
            add(HomeData("2022.01.26","하하", R.drawable.ic_launcher_background,"#가 #나 #다"))
            add(HomeData("2022.12.26","호호", R.drawable.ic_rectangle55,"#라마 #취업 #참여"))
            add(HomeData("2022.09.16","히히", R.drawable.ic_splash_screen,"#박람회 #취업 #가나다"))
        }

        val jobAdapter = HomeAdapter(jobDatas)
        binding.homeRvJob.adapter = jobAdapter
        binding.homeRvJob.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        togetherDatas.apply {
            add(HomeData("2022.12.26","호호",R.drawable.profile,"#라마 #취업 #참여"))
            add(HomeData("2022.09.16","히히", R.drawable.home,"#박람회 #취업 #가나다"))
        }

        val togetherAdapter = HomeAdapter(togetherDatas)
        binding.homeRvTogether.adapter = togetherAdapter
        binding.homeRvTogether.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        personDatas.apply {
            add(HomeData("2022.02.06","직무", R.drawable.ic_launcher_background,"#박람회 #취업 #참여"))
            add(HomeData("2022.03.08","역량",R.drawable.profile,"#직무 #역량 #굿"))
            add(HomeData("2022.09.16","히히", R.drawable.home_background,"#박람회 #취업 #가나다"))
        }

        val personAdapter = HomeAdapter(personDatas)
        binding.homeRvPerson.adapter = personAdapter
        binding.homeRvPerson.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)


        return binding.root
    }
}