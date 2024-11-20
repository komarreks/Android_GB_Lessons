package com.example.recyclerviewexample.prersentation.recycler

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclerviewexample.databinding.PhotoViewBinding
import com.example.recyclerviewexample.model.Photo

class Adapter(
    private val onClick: (Photo) -> Unit
): RecyclerView.Adapter<PhotoHolder>() {

    private var photos: List<Photo> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {
        return PhotoHolder(PhotoViewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    fun changeData(photosList: List<Photo>){
        photos = photosList
        Log.d("Размер списка ", photos.size.toString())
            notifyDataSetChanged()
        Log.d("notifyDataSetChanged ", "")
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
        val photofrom = photos[position]

        with(holder.binding){
            roverText.text = "Rover: ${photofrom.rover?.name}"
            cameraText.text = "Camera: ${photofrom.camera?.name}"
            solText.text = "Sol: ${photofrom.sol.toString()}"
            dateText.text = "Date: ${photofrom.earth_date}"

            Glide.with(photo).load(photofrom.img_src).into(holder.binding.photo)
        }

        holder.binding.root.setOnClickListener { onClick(photofrom) }
    }
}

class PhotoHolder(val binding: PhotoViewBinding): RecyclerView.ViewHolder(binding.root)