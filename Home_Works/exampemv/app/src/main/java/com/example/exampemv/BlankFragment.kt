package com.example.exampemv

import android.os.Bundle
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.exampemv.databinding.FragmentBlankBinding

class BlankFragment : Fragment() {

    private var _binding:FragmentBlankBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBlankBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progress.isVisible = false

        initButton()
        initTextInput()

        enableButton(binding.searchText.length())

        viewLifecycleOwner.lifecycleScope
            .launchWhenStarted {
                viewModel.state
                    .collect{state ->
                        when(state){
                            State.Running -> setStates(true)
                            State.Waiting -> setStates(false)
                        }
                    }
            }
        viewLifecycleOwner.lifecycleScope
            .launchWhenStarted {
                viewModel.resultSearch.collect{message ->
                    binding.resultSearch.text = message
                }
            }
    }

    private fun initTextInput(){
        binding.searchText.addTextChangedListener{watcher ->
            enableButton(watcher!!.length)
        }
    }

    private fun initButton(){
        binding.button.setOnClickListener {
            binding.resultSearch.text = getString(R.string.searhing)
            viewModel.onSearch(binding.searchText.text.toString())
        }
    }

    private fun enableButton(lenghtOfSearch: Int){
        binding.button.isEnabled = lenghtOfSearch>3
    }

    private fun setStates(searchIsRunnig: Boolean){
        binding.button.isEnabled = !searchIsRunnig
        binding.progress.isVisible = searchIsRunnig
        enableButton(binding.searchText.length())
    }
}