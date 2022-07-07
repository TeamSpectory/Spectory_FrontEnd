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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spectory.databinding.FragmentArchivingBinding
import com.example.spectory.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), ArchiveView, ProfileView, DeletePostView {

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

        //리사이클러뷰의 각각 아이템을 클릭했을 때
        togetherAdapter.setMyItemClickListener(object: HomeAdapter.MyItemClickListener{
            override fun onItemClick(archive: PostResponse) {
                //DetailAcitivy 열기
                //로그 찍어서 postIdx 받아오기
                Log.d("postIdx",archive.postIdx.toString())
                //해당 postIdx로 가득찬 DetailActivity 열기
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("postIdx",archive.postIdx.toString())
                startActivity(intent)
            }

            override fun onDeleteClick(archive: PostResponse) {
                Log.d("게시글 삭제","게시글이 삭제되었습니다")
                //게시글 삭제 서버 연동
                //deletePost()
            }
        })

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

    private fun getToken(): TokenData{
        val spf = this.activity?.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)

        val tokenData = TokenData("")
        tokenData.token = spf!!.getString("jwt","")!!
        Log.d("TOKEN",tokenData.token)
        return tokenData
    }

    private fun deletePost() {
        val authService = AuthService()
        authService.setDeletePostView(this)

        authService.deletePost(getUserIdx(),getToken())
    }


    override fun onDeletePostSuccess(status: Int) {
        when(status){
            200 -> {
                Toast.makeText(this.context,"게시글이 삭제되었습니다", Toast.LENGTH_SHORT).show()

            }
        }
    }

    override fun onDeletePostFailure() {
        TODO("Not yet implemented")
    }
}