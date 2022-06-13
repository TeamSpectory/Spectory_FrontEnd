package com.example.spectory

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.spectory.databinding.ActivityLoginBinding
import com.example.spectory.databinding.ActivityWriteBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class WriteActivity: AppCompatActivity(), WriteView {

    lateinit var binding: ActivityWriteBinding
    private var star: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteBinding.inflate(layoutInflater)

        //write()

        //activity 종료
        binding.writeCancelBtn.setOnClickListener {
            closeWriteActivity()
        }
        binding.writeCloseBtn.setOnClickListener {
            closeWriteActivity()
        }
        binding.writeOkBtn.setOnClickListener {
            writeTest()
            //closeWriteActivity()
        }



        star()
        setContentView(binding.root)
    }

    private fun closeWriteActivity(){
        finish()
    }

    //별 눌렀을 때 사진 변경
    private fun star(){
        binding.writeStar1.setOnClickListener {
            star = 1
            binding.writeStar1.setImageResource(R.drawable.star_full)
            binding.writeStar2.setImageResource(R.drawable.ic_star_)
            binding.writeStar3.setImageResource(R.drawable.ic_star_)
            binding.writeStar4.setImageResource(R.drawable.ic_star_)
            binding.writeStar5.setImageResource(R.drawable.ic_star_)
        }
        binding.writeStar2.setOnClickListener {
            star = 2
            binding.writeStar1.setImageResource(R.drawable.star_full)
            binding.writeStar2.setImageResource(R.drawable.star_full)
            binding.writeStar3.setImageResource(R.drawable.ic_star_)
            binding.writeStar4.setImageResource(R.drawable.ic_star_)
            binding.writeStar5.setImageResource(R.drawable.ic_star_)
        }
        binding.writeStar3.setOnClickListener {
            star = 3
            binding.writeStar1.setImageResource(R.drawable.star_full)
            binding.writeStar2.setImageResource(R.drawable.star_full)
            binding.writeStar3.setImageResource(R.drawable.star_full)
            binding.writeStar4.setImageResource(R.drawable.ic_star_)
            binding.writeStar5.setImageResource(R.drawable.ic_star_)
        }
        binding.writeStar4.setOnClickListener {
            star = 4
            binding.writeStar1.setImageResource(R.drawable.star_full)
            binding.writeStar2.setImageResource(R.drawable.star_full)
            binding.writeStar3.setImageResource(R.drawable.star_full)
            binding.writeStar4.setImageResource(R.drawable.star_full)
            binding.writeStar5.setImageResource(R.drawable.ic_star_)
        }
        binding.writeStar5.setOnClickListener {
            star = 5
            binding.writeStar1.setImageResource(R.drawable.star_full)
            binding.writeStar2.setImageResource(R.drawable.star_full)
            binding.writeStar3.setImageResource(R.drawable.star_full)
            binding.writeStar4.setImageResource(R.drawable.star_full)
            binding.writeStar5.setImageResource(R.drawable.star_full)
        }
    }

    private fun write() {
        val songService = AuthService()
        songService.setWriteView(this)
        songService.write(getWriteData())
        //songService.write(WriteData(1,"","","","","","",3,"",2))
    }

    //입력한 값들로부터 UserData 객체 받아오기
    private fun getWriteData(): WriteData {
        //post 객체 선언 후 입력된 값들 저장해서 서버에 전송
        //val type: Int = 1
        val title: String = binding.writeTitle.text.toString()
        val startDate: String = binding.writeDate.text.toString()
        val endDate: String = binding.writeDate.text.toString()
        val situation: String = binding.writeSituation.text.toString()
        val action: String = binding.writeAction.text.toString()
        val learned: String = binding.writeLearned.text.toString()
        val rates: Int = star
        val tags: String = binding.writeTag1.text.toString()+binding.writeTag2.text.toString()+binding.writeTag3.text.toString()
        val userIdx: Int = getUserIdx()

        return WriteData(1,title,startDate,endDate,situation,action,learned,rates,tags,userIdx)
        Log.d("writeData",WriteData(1,title,startDate,endDate,situation,action,learned,rates,tags,userIdx).toString())
    }

    override fun onWriteSuccess(status: Int, message: String, data: Data) {
        when(status){
            200 -> {
                Log.d("게시글쓰기","게시글 쓰기 성공")
            }
        }
    }

    override fun onWriteFailure() {
        TODO("Not yet implemented")
    }

    private fun getUserIdx(): Int{
        val spf = this.getSharedPreferences("auth",AppCompatActivity.MODE_PRIVATE)

        return spf!!.getInt("userIdx",0)
    }

    private fun writeTest(){
        val authService = getRetrofit().create(WriteRetrofitInterface::class.java)
        authService.write(WriteData(2,"","","","","","",3,"",16)).enqueue(object: Callback<AuthResponse>{
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                Log.d("WRITE/SUCCESS",response.toString())

            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Log.d("WRITE/FAIL",t.message.toString())
            }

        })
        Log.d("WRITE","HELLO")
    }

}