package com.example.spectory

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spectory.databinding.FragmentArchivingBinding
import com.example.spectory.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), ArchiveView, ProfileView {

    lateinit var binding: FragmentHomeBinding
    private var jobDatas = ArrayList<PostResponse>()
    private var togetherDatas = ArrayList<PostResponse>()
    private var personDatas = ArrayList<PostResponse>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        //+버튼 누르면 write activity로 이동
        binding.homeGoToWrite.setOnClickListener {
            val intent = Intent(context, WriteActivity::class.java)
            startActivity(intent)
        }

        getNickname()
        archive()
        return binding.root
    }

    private fun archive() {
        val authService = AuthService()
        authService.setArchiveView(this)

        authService.archiving(getUserIdx())
    }

    private fun getUserIdx(): Int {
        val spf = this.activity?.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)

        return spf!!.getInt("userIdx", 0)
    }

    //닉네임 받아오기
//    private fun getNickname(): String {
//        val spf = this.activity?.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
//
//        return spf!!.getString("nickname", "")!!
//    }

    override fun onArchiveSuccess(resp: List<PostResponse>) {
        for (i in resp) {
            if (i.type == 1) {
                jobDatas.add(i)
            } else if (i.type == 2) {
                togetherDatas.add(i)
            } else if (i.type == 3) {
                personDatas.add(i)
            }
        }

        val jobAdapter = HomeAdapter(jobDatas)
        binding.homeRvJob.adapter = jobAdapter
        binding.homeRvJob.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.homeJobTv.text = "> 나의 직무 역량 ("+jobDatas.size+")"

        val togetherAdapter = HomeAdapter(togetherDatas)
        binding.homeRvTogether.adapter = togetherAdapter
        binding.homeRvTogether.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.homeTogetherTv.text = "> 나의 협업 경험 ("+togetherDatas.size+")"

        val personAdapter = HomeAdapter(personDatas)
        binding.homeRvPerson.adapter = personAdapter
        binding.homeRvPerson.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.homePersonTv.text = "> 나의 성격 및 가치관 ("+personDatas.size+")"

    }

    override fun onArchiveFailure() {
        TODO("Not yet implemented")
    }


    private fun getNickname() {
        val authService = AuthService()
        authService.setProfileView(this)

        authService.myprofile(getUserIdx())
    }

    override fun onProfileSuccess(status: Int, data: Data) {
        when(status){
            200 -> {
                binding.homeName.text = "안녕하세요\n"+data.nickname+"님!"
            }
        }
    }

    override fun onProfileFailure() {
        TODO("Not yet implemented")
    }
}