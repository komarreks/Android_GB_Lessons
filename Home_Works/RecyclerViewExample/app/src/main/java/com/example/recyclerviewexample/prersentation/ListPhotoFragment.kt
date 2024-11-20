package com.example.recyclerviewexample.prersentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.recyclerviewexample.R
import com.example.recyclerviewexample.databinding.FragmentListPhotoBinding
import com.example.recyclerviewexample.model.Photo
import com.example.recyclerviewexample.prersentation.recycler.Adapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

class ListPhotoFragment : Fragment() {

    private var _binding:FragmentListPhotoBinding? = null
    val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()
    val photoAdapter = Adapter{photo: Photo ->  onPhotoClick(photo)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListPhotoBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.photoRecycler.adapter = photoAdapter

        viewModel.photoList.onEach {
            photoAdapter.changeData(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun onPhotoClick(photo: Photo){
        val bundle = Bundle()
        bundle.putParcelable("photo", photo)
        findNavController().navigate(R.id.onePhotoFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}