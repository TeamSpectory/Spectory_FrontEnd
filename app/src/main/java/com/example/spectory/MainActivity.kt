package com.example.spectory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.spectory.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBottomNavigation() //하단바를 통한 fragment 이동

        Log.d("MAIN/JWT_TO_SERVER", getJwt().toString())
    }

    private fun getJwt(): String?{
        val spf = this.getSharedPreferences("auth",AppCompatActivity.MODE_PRIVATE)

        return spf!!.getString("jwt","")
    }

    //하단바 함수
    private fun initBottomNavigation(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, HomeFragment())
            .commitAllowingStateLoss()

        binding.mainBnv.setOnItemSelectedListener { item ->
            when (item.itemId){
                R.id.homeFragment ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, HomeFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.archivingFragment-> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, ArchivingFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.myprofileFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, MyprofileFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }



}