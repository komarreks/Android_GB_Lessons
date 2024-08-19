package com.example.components

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.components.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var timerValue = binding.slider.value.toInt()
        var timerIsActive = false

        fun updateUI(value: Float) {
            when (timerIsActive) {
                true -> {
                    binding.slider.isEnabled = false
                    binding.buttonStart.visibility = View.GONE
                    binding.buttonStop.visibility = View.VISIBLE
                    binding.timer.text = (timerValue - value.toInt()).toString()
                    binding.progressBar.progress = timerValue - value.toInt()
                }
                false -> {
                    binding.timer.text = value.toInt().toString()
                    binding.progressBar.max = timerValue
                    binding.progressBar.progress = timerValue
                    binding.buttonStop.visibility = View.GONE
                    binding.buttonStart.visibility = View.VISIBLE
                    binding.slider.isEnabled = true
                }
            }
        }

        fun finishTimer() {
            timerIsActive = false
            Toast.makeText(this, "Timer Task Finished", Toast.LENGTH_SHORT).show()
            updateUI(timerValue.toFloat())
        }

        binding.slider.addOnChangeListener { _, value, _ ->
            timerValue = value.toInt()
            updateUI(value)
        }

        binding.buttonStart.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                timerIsActive = true
                (1..timerValue).asFlow().collect {
                    binding.buttonStop.setOnClickListener {
                        finishTimer()
                        cancel()
                    }
                    updateUI(it.toFloat())
                    delay(1000)
                }
                finishTimer()
            }
        }
    }
}
