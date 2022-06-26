package com.example.spectory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.spectory.databinding.ArchivingListBinding
import com.example.spectory.databinding.JobListBinding

class HomeAdapter(private val homeDataList: ArrayList<PostResponse>): RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    interface MyItemClickListener{
        fun onItemClick(archive: PostResponse)
    }

    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener: MyItemClickListener){
        mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        val binding: JobListBinding = JobListBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {
        holder.bind(homeDataList[position])
        holder.itemView.setOnClickListener {
            mItemClickListener.onItemClick(homeDataList[position])
        }
    }

    override fun getItemCount(): Int {
        return homeDataList.size
    }

    inner class ViewHolder(val binding: JobListBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(homeData: PostResponse){
            binding.jobListDate.text = homeData.startDate
            //binding.jobListImg.setImageResource(homeData.homeImg!!)
            binding.jobListName.text = homeData.title
            binding.jobListTag.text = "#"+homeData.tags.split("/")[0]+" #"+homeData.tags.split("/")[1]+" #"+homeData.tags.split("/")[2]
        }
    }

}