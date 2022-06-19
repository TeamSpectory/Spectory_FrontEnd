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
            write()
            closeWriteActivity()
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
        val tags: String = binding.writeTag1.text.toString()+"/"+binding.writeTag2.text.toString()+"/"+binding.writeTag3.text.toString()
        val userIdx: Int = getUserIdx()

        return WriteData(2,title,startDate,endDate,situation,action,learned,rates,tags,userIdx)
       // Log.d("writeData",WriteData(1,title,startDate,endDate,situation,action,learned,rates,tags,userIdx).toString())
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

    private fun write(){
        val authService = getRetrofit().create(WriteRetrofitInterface::class.java)
        //Log.d("TEST",WriteData(1,"제목","2022-10-11","2022-10-12","상황","행동","배운 것",4,"d/d/d",20).toString())
        authService.write(getWriteData()).enqueue(object:Callback<WriteResponse>{
            override fun onResponse(call: Call<WriteResponse>, response: Response<WriteResponse>) {
                Log.d("WRITE/SUCCESS",response.toString())
            }

            override fun onFailure(call: Call<WriteResponse>, t: Throwable) {
                Log.d("WRITE/FAIL",t.message.toString())
            }

        })
        Log.d("WRITE","HELLO")
    }

}