package ru.app

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import ru.app.databinding.FragmentStartBinding
import java.text.SimpleDateFormat

class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartBinding.inflate(inflater)

        return binding.root
    }

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.startButton.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_quizeFragment)
        }

        binding.dateButton.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText(resources.getString(R.string.enter_birthday))
                .build()


            datePicker.addOnPositiveButtonClickListener {timeInMillis ->
                val sdf = SimpleDateFormat("dd-MM-yyyy")
                Snackbar.make(binding.dateButton, sdf.format(timeInMillis), Snackbar.LENGTH_LONG).show()
            }

            datePicker.show(parentFragmentManager, "DatePicker")
        }
    }
}