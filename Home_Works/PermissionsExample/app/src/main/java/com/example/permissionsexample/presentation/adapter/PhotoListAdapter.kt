package com.example.permissionsexample.presentation.adapter

import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.permissionsexample.R
import com.example.permissionsexample.databinding.PhotoLayoutBinding
import com.example.permissionsexample.model.PathToImage
import java.io.File

class PhotoListAdapter():RecyclerView.Adapter<PhotoViewHolder>() {

    private var photoList:List<PathToImage> = listOf()

    @SuppressLint("NotifyDataSetChanged")
    fun update(photos: List<PathToImage>){
        photoList = photos
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = PhotoLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PhotoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val pathToImage = photoList[position]
        holder.binding.name.text = pathToImage.name


        Glide.with(holder.binding.root)
            .load(Uri.parse(pathToImage.path))
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_foreground)
            .circleCrop()
            .into(holder.binding.photo)
    }
}

class PhotoViewHolder(val binding: PhotoLayoutBinding):RecyclerView.ViewHolder(binding.root)