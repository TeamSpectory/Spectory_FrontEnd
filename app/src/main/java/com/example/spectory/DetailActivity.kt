package com.example.spectory

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.spectory.databinding.ActivityDetailBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.properties.Delegates


class DetailActivity: AppCompatActivity(), DetailView {
    lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)

        val postIdx : String? = intent.getStringExtra("postIdx")

        detail(postIdx!!.toInt())

        binding.writeOkBtn.setOnClickListener {
            finish()
        }

        setContentView(binding.root)
    }

    override fun onDetailSuccess(status : Int, resp: DetailResponse) {
        when(status){
            200 -> {
                binding.detailTitle.text = resp.detailData.title
                binding.detailDate.text = resp.detailData.endDate
                binding.detailSituation.text = resp.detailData.situation
                binding.detailAction.text = resp.detailData.action
                binding.detailLearned.text = resp.detailData.learned
                binding.detailTag1.text = resp.detailData.tags.split("/")[0]
                binding.detailTag2.text = resp.detailData.tags.split("/")[1]
                binding.detailTag3.text = resp.detailData.tags.split("/")[2]
                //별 정리
                star(resp.detailData.rates)
                Glide.with(binding.detailImg.context)
                    .load(resp.detailData.picture)
                    .into(binding.detailImg)
            }
        }
    }

    override fun onDetailFailure() {
        TODO("Not yet implemented")
        Log.d("DETAIL/여기가 되어야 한다","제바아아랑랃라다아알아랑랑랑라")
    }

    private fun detail(postIdx: Int) {
        val authService = AuthService()
        authService.setDetailView(this)

        authService.detail(postIdx)
    }

    //별 눌렀을 때 사진 변경
    private fun star(rates: Int){
        if(rates == 1) {
            binding.detailStar1.setImageResource(R.drawable.star_full)
            binding.detailStar2.setImageResource(R.drawable.ic_star_)
            binding.detailStar3.setImageResource(R.drawable.ic_star_)
            binding.detailStar4.setImageResource(R.drawable.ic_star_)
            binding.detailStar5.setImageResource(R.drawable.ic_star_)
        }
        else if(rates == 2) {
            binding.detailStar1.setImageResource(R.drawable.star_full)
            binding.detailStar2.setImageResource(R.drawable.star_full)
            binding.detailStar3.setImageResource(R.drawable.ic_star_)
            binding.detailStar4.setImageResource(R.drawable.ic_star_)
            binding.detailStar5.setImageResource(R.drawable.ic_star_)
        }
        else if(rates == 3) {
            binding.detailStar1.setImageResource(R.drawable.star_full)
            binding.detailStar2.setImageResource(R.drawable.star_full)
            binding.detailStar3.setImageResource(R.drawable.star_full)
            binding.detailStar4.setImageResource(R.drawable.ic_star_)
            binding.detailStar5.setImageResource(R.drawable.ic_star_)
        }
        else if(rates == 4) {
            binding.detailStar1.setImageResource(R.drawable.star_full)
            binding.detailStar2.setImageResource(R.drawable.star_full)
            binding.detailStar3.setImageResource(R.drawable.star_full)
            binding.detailStar4.setImageResource(R.drawable.star_full)
            binding.detailStar5.setImageResource(R.drawable.ic_star_)
        }
        else if(rates == 5) {
            binding.detailStar1.setImageResource(R.drawable.star_full)
            binding.detailStar2.setImageResource(R.drawable.star_full)
            binding.detailStar3.setImageResource(R.drawable.star_full)
            binding.detailStar4.setImageResource(R.drawable.star_full)
            binding.detailStar5.setImageResource(R.drawable.star_full)
        }
    }
}