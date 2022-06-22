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

class ArchivingFragment: Fragment(), ArchiveView {

    lateinit var binding : FragmentArchivingBinding
    private var archiveDatas = ArrayList<PostResponse>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArchivingBinding.inflate(inflater,container,false)

//        archiveDatas.apply {
//            add(ArchiveData("22.01.23~22.01.26",R.drawable.profile,"5.0","OO 공모전","직무역량","성격 및 가치관","협업 경험","동아리","앱 프로젝트","주도적 참여"))
//            add(ArchiveData("22.01.23~22.01.26", R.drawable.ic_launcher_background,"3.0","공모전 3","직무역량","성격 및 가치관","협업 경험","웹 프로젝트","동아리","디자인"))
//            add(ArchiveData("22.01.23~22.01.26",
//                R.drawable.home,"3.0","프로젝트","직무역량","성격 및 가치관","협업 경험","동아리","앱 프로젝트","주도적 참여"))
//            add(ArchiveData("22.01.23~22.01.26",
//                R.drawable.ic_bookmark,"1.0","스펙토리","직무역량","성격 및 가치관","협업 경험","동아리","앱 프로젝트","주도적 참여"))
//            add(ArchiveData("22.01.23~22.01.26",R.drawable.profile,"3.0","얍","직무역량","성격 및 가치관","협업 경험","동아리","앱 프로젝트","주도적 참여"))
//            add(ArchiveData("22.01.23~22.01.26",R.drawable.profile,"5.0","히히 공모전","직무역량","성격 및 가치관","협업 경험","동아리","앱 프로젝트","주도적 참여"))
//        }


        archive()

        Log.d("이것도 되라되라돌다러디아아악",archiveDatas.toString())

//        val archiveAdapter = ArchiveAdapter(archiveDatas)
//        binding.archiveRv.adapter = archiveAdapter
//        binding.archiveRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        return binding.root
    }

    private fun archive(){
        val authService = AuthService()
        authService.setArchiveView(this)

        authService.archiving(getUserIdx())
    }

    private fun getUserIdx(): Int{
        val spf = this.activity?.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)

        return spf!!.getInt("userIdx",0)
    }

    override fun onArchiveSuccess(resp: List<PostResponse>) {
        Log.d("히히히히ㅣ히히히ㅣ히",resp.toString())
        for (i in resp){
            Log.d("으악앙가아아아아악",i.toString())
            archiveDatas.add(i)
            Log.d("되라되라되라되러ㅏ",archiveDatas.toString())
        }
        val archiveAdapter = ArchiveAdapter(archiveDatas)
        binding.archiveRv.adapter = archiveAdapter
        binding.archiveRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        //리사이클러뷰의 각각 아이템을 클릭했을 때
        archiveAdapter.setMyItemClickListener(object: ArchiveAdapter.MyItemClickListener{
            override fun onItemClick(archive: PostResponse) {
                //DetailAcitivy 열기
                //로그 찍어서 postIdx 받아오기
                Log.d("postIdx",archive.postIdx.toString())
                //해당 postIdx로 가득찬 DetailActivity 열기
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("postIdx",archive.postIdx.toString())
                startActivity(intent)
            }
        })
    }


    override fun onArchiveFailure() {
        TODO("Not yet implemented")
    }
}