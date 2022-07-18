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
    private var archiveDatas = ArrayList<PostList>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArchivingBinding.inflate(inflater,container,false)

        archive()

        Log.d("이것도 되라되라돌다러디아아악",archiveDatas.toString())

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

    override fun onArchiveSuccess(resp: PostResponse) {
        Log.d("ARCHIVE","아카이빙 화면 아카이빙 성공")

        Log.d("히히히히ㅣ히히히ㅣ히",resp.toString())

        for (i in resp.data){
            Log.d("으악앙가아아아아악",i.toString())
            archiveDatas.add(i)
            Log.d("되라되라되라되러ㅏ",archiveDatas.toString())
        }
        val archiveAdapter = ArchiveAdapter(archiveDatas)
        binding.archiveRv.adapter = archiveAdapter
        binding.archiveRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        //리사이클러뷰의 각각 아이템을 클릭했을 때
        archiveAdapter.setMyItemClickListener(object: ArchiveAdapter.MyItemClickListener{
            override fun onItemClick(archive: PostList) {
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