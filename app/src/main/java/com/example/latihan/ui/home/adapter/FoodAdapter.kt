package com.example.latihan.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.latihan.databinding.ItemJadwalBinding
import com.example.latihan.model.Jadwal

class FoodAdapter(val dataFood: (Jadwal)->Unit):ListAdapter<Jadwal,FoodAdapter.FoodViewHolder>(DIFF_CALLBACK) {

     inner class FoodViewHolder(private val binding: ItemJadwalBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(jadwal: Jadwal){
            binding.tvNameFood.text = jadwal.hari
            itemView.setOnClickListener{
                dataFood.invoke(jadwal)
            }
        }

    }

    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Jadwal>(){
            override fun areItemsTheSame(oldItem: Jadwal, newItem: Jadwal): Boolean =
                oldItem == newItem
            override fun areContentsTheSame(oldItem: Jadwal, newItem: Jadwal): Boolean =
                oldItem.id == newItem.id

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val binding = ItemJadwalBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FoodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}