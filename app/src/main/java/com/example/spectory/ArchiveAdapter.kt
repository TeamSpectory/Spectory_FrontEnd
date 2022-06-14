package com.example.spectory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.spectory.databinding.ArchivingListBinding

class ArchiveAdapter(private val archiveDataList: ArrayList<PostResponse>): RecyclerView.Adapter<ArchiveAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ArchiveAdapter.ViewHolder {
        val binding: ArchivingListBinding = ArchivingListBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArchiveAdapter.ViewHolder, position: Int) {
        holder.bind(archiveDataList[position])
   }

    override fun getItemCount(): Int {
        return archiveDataList.size
    }

    inner class ViewHolder(val binding: ArchivingListBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(archiveData: PostResponse){
            binding.arcListDate.text = archiveData.startDate+"~"+archiveData.endDate
            //binding.arcListImg.setImageResource(archiveData.picture!!)

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