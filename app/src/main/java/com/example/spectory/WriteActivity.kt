package com.example.spectory

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.spectory.databinding.ActivityWriteBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.lang.Exception
import java.util.*


class WriteActivity : AppCompatActivity(), WriteView {

    lateinit var binding: ActivityWriteBinding
    private var star: Int = 0
    lateinit var imageFile: File
    private val GALLERY = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteBinding.inflate(layoutInflater)

        //write()

        //갤러리에서 사진 선택
        binding.writeImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.setType("image/*")
            startActivityForResult(intent, GALLERY)
        }

        //activity 종료
        binding.writeCancelBtn.setOnClickListener {
            closeWriteActivity()
        }
        binding.writeCloseBtn.setOnClickListener {
            closeWriteActivity()
        }
        binding.writeOkBtn.setOnClickListener {
            Log.d("imageFile", imageFile.toString())
            writeImage(
                "2", binding.writeTitle.text.toString(),
                binding.writeDate.text.toString(),
                binding.writeDate.text.toString(),
                binding.writeSituation.text.toString(),
                binding.writeAction.text.toString(),
                binding.writeLearned.text.toString(),
                star,
                binding.writeTag1.text.toString() + "/" + binding.writeTag2.text.toString() + "/" + binding.writeTag3.text.toString(),
                getUserIdx(),
                imageFile
            )
            closeWriteActivity()
        }

        star()
        setContentView(binding.root)
    }

    private fun bitmapToFile(bitmap: Bitmap): File {
        // Get the context wrapper
        val wrapper = ContextWrapper(applicationContext)

        // Initialize a new file instance to save bitmap object
        var file = wrapper.getDir("Images", Context.MODE_PRIVATE)
        file = File(file, "${UUID.randomUUID()}.jpg")

        try {
            // Compress the bitmap and save in jpg format
            val stream: OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 20, stream)
            stream.flush()
            stream.close()

        } catch (e: IOException) {
            e.printStackTrace()
        }

        // Return the saved bitmap uri
        return file
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == GALLERY) {
                var ImageData: Uri? = data?.data
                Toast.makeText(this, ImageData.toString(), Toast.LENGTH_SHORT).show()
                try {
                    val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, ImageData)
                    binding.writeImage.setImageBitmap(bitmap)
                    //비트맵 구했으니까 이제 파일로 바꾸면 됨
                    imageFile = bitmapToFile(bitmap)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

    }


    private fun closeWriteActivity() {
        finish()
    }

    //별 눌렀을 때 사진 변경
    private fun star() {
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


    override fun onWriteSuccess(status: Int) {
        when (status) {
            200 -> {
                Log.d("게시글쓰기", "게시글 쓰기 성공")
            }
        }
    }

    override fun onWriteFailure() {
        TODO("Not yet implemented")
    }

    private fun getUserIdx(): Int {
        val spf = this.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)

        return spf!!.getInt("userIdx", 0)
    }


    private fun writeImage(
        type: String,
        title: String,
        startDate: String,
        endDate: String,
        situation: String,
        action: String,
        learned: String,
        rates: Int,
        tags: String,
        userIdx: Int,
        image: File
    ) {
        val authService = AuthService()
        authService.setWriteView(this)
        authService.writeImage(
            type,
            title,
            startDate,
            endDate,
            situation,
            action,
            learned,
            rates,
            tags,
            userIdx,
            image
        )
    }

}