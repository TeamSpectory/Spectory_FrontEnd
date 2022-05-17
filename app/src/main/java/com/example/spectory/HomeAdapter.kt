package com.example.spectory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.spectory.databinding.ArchivingListBinding
import com.example.spectory.databinding.JobListBinding

class HomeAdapter(private val homeDataList: ArrayList<HomeData>): RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        val binding: JobListBinding = JobListBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {
        holder.bind(homeDataList[position])
    }

    override fun getItemCount(): Int {
        return homeDataList.size
    }

    inner class ViewHolder(val binding: JobListBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(homeData: HomeData){
            binding.jobListDate.text = homeData.homeDate
            binding.jobListImg.setImageResource(homeData.homeImg!!)
            binding.jobListName.text = homeData.homeName
            binding.jobListTag.text = homeData.homeTag
        }
    }

}