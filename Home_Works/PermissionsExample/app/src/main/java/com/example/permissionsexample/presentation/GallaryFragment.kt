package com.example.permissionsexample.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.permissionsexample.App
import com.example.permissionsexample.R
import com.example.permissionsexample.databinding.FragmentGallaryBinding
import com.example.permissionsexample.presentation.adapter.PhotoListAdapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class GallaryFragment : Fragment() {

    private var _binding: FragmentGallaryBinding? = null
    val binding get() = _binding!!
    private val photoAdapter = PhotoListAdapter()
    private val model:Model by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGallaryBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.openCreatePhotoButton.setOnClickListener{
            findNavController().navigate(R.id.action_gallaryFragment_to_createPhotoFragment)
        }

        binding.allPhoto.adapter = photoAdapter

        model.photos.onEach {
            photoAdapter.update(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}