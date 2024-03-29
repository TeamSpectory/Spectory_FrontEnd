package com.example.spectory

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.parseAsHtml
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.spectory.databinding.ArchivingListBinding
import java.io.InputStream
import java.lang.Exception

class ArchiveAdapter(private val archiveDataList: ArrayList<PostList>): RecyclerView.Adapter<ArchiveAdapter.ViewHolder>() {

    interface MyItemClickListener{
        fun onItemClick(archive: PostList)
    }

    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener: MyItemClickListener){
        mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ArchiveAdapter.ViewHolder {
        val binding: ArchivingListBinding = ArchivingListBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArchiveAdapter.ViewHolder, position: Int) {
        holder.bind(archiveDataList[position])
        holder.itemView.setOnClickListener {
            mItemClickListener.onItemClick(archiveDataList[position])
        }
   }

    override fun getItemCount(): Int {
        return archiveDataList.size
    }

    inner class ViewHolder(val binding: ArchivingListBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(archiveData: PostList){
            binding.arcListDate.text = archiveData.startDate+"~"+archiveData.endDate

            Glide.with(binding.arcListImg.context)
                .load(archiveData.picture)
                .into(binding.arcListImg)

            //type 별로 체크 표시
            if(archiveData.type == 1){
                binding.arcListType1.setImageResource(R.drawable.ic_check_box)
            }
            else if(archiveData.type == 2){
                binding.arcListType2.setImageResource(R.drawable.ic_check_box)
            }
            else if(archiveData.type == 3){
                binding.arcListType3.setImageResource(R.drawable.ic_check_box)
            }

            binding.arcListName.text=archiveData.title
            binding.arcListStar.text=archiveData.rates.toString()+".0"

            //tag 분리해서 각각 적용하기
            binding.arcListTag1.text=archiveData.tags.split("/")[0]
            binding.arcListTag2.text=archiveData.tags.split("/")[1]
            binding.arcListTag3.text=archiveData.tags.split("/")[2]

        }
    }

}