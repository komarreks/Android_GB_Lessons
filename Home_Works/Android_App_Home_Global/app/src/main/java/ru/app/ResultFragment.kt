package ru.app

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.fragment.app.FragmentResultOwner
import androidx.navigation.fragment.findNavController
import ru.app.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private var countCorrectAnswers: Int? = null
    private var countQuestions: Int? = null

    private var _binding:FragmentResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            countCorrectAnswers = it.getInt(ARG_PARAM1)
            countQuestions = it.getInt(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultBinding.inflate(inflater)

        binding.toStartButton.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_startFragment)
        }
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.resultLabel.text = "Верно $countCorrectAnswers/$countQuestions"

        var scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1.5f,1f)
        var scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1.5f,1f)

        ObjectAnimator.ofPropertyValuesHolder(binding.yourResultLabel, scaleX, scaleY).apply {
            duration = 1000
            interpolator = AccelerateDecelerateInterpolator()
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.RESTART
            start()
        }

        ObjectAnimator.ofPropertyValuesHolder(binding.toStartButton, scaleX, scaleY).apply {
            duration = 1000
            interpolator = AccelerateDecelerateInterpolator()
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.RESTART
            start()
        }
    }

    companion object {
        const val ARG_PARAM1 = "countCorrectAnswers"
        const val ARG_PARAM2 = "countQuestions"

        fun newInstance(param1: Int, param2: Int) =
            ResultFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                    putInt(ARG_PARAM2, param2)
                }
            }
    }
}