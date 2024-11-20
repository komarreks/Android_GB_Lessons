package com.example.recyclerviewexample.prersentation

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.example.recyclerviewexample.databinding.FragmentOnePhotoBinding
import com.example.recyclerviewexample.model.Photo


class OnePhotoFragment : Fragment() {

    private var _binding: FragmentOnePhotoBinding? = null
    val bindind get() = _binding!!
    private var photo:Photo? = null

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            photo = it.getParcelable(PHOTO_KEY, Photo::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnePhotoBinding.inflate(inflater)
        return bindind.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindind.roverText.text = "Rover: ${photo?.rover?.name}"
        bindind.cameraText.text = "Camera: ${photo?.camera?.name}"
        bindind.solText.text = "Sol: ${photo?.sol.toString()}"
        bindind.dateText.text = "Date: ${photo?.earth_date}"

        Glide.with(bindind.photo).load(photo?.img_src).into(bindind.photo)
    }

    companion object{
        const val PHOTO_KEY = "photo"
    }



}