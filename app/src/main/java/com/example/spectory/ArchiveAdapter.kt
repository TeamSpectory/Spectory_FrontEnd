package com.example.spectory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.spectory.databinding.ArchivingListBinding

class ArchiveAdapter(private val archiveDataList: ArrayList<ArchiveData>): RecyclerView.Adapter<ArchiveAdapter.ViewHolder>() {

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

        fun bind(archiveData: ArchiveData){
            binding.arcListDate.text = archiveData.arcDate
            binding.arcListImg.setImageResource(archiveData.arcImg!!)
            binding.arcListJob.text=archiveData.arcJob
            binding.arcListName.text=archiveData.arcName
            binding.arcListPerson.text=archiveData.arcPerson
            binding.arcListStar.text=archiveData.arcStar
            binding.arcListTogether.text=archiveData.arcTogether
            binding.arcListTag1.text=archiveData.arcTag1
            binding.arcListTag2.text=archiveData.arcTag2
            binding.arcListTag3.text=archiveData.arcTag3

        }
    }

}