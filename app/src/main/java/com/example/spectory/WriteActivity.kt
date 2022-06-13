package com.example.spectory

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.spectory.databinding.ActivityLoginBinding
import com.example.spectory.databinding.ActivityWriteBinding

class WriteActivity: AppCompatActivity() {

    lateinit var binding: ActivityWriteBinding
    private var star: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteBinding.inflate(layoutInflater)

        //activity 종료
        binding.writeCancelBtn.setOnClickListener {
            closeWriteActivity()
        }
        binding.writeCloseBtn.setOnClickListener {
            closeWriteActivity()
        }
        binding.writeOkBtn.setOnClickListener {
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

}