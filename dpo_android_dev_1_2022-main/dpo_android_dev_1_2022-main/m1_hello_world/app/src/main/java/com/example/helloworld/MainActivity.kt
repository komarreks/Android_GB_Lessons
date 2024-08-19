package com.example.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.helloworld.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var counter = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val averageStatus = binding.status.context.getText(R.string.average_status)
        binding.plus.setOnClickListener {
            counter++
            binding.status.text = "$averageStatus ${50 - counter}"
            binding.counter.text = counter.toString()
            binding.status.setTextColor(getColorStateList(R.color.blue))
            if (counter >= 50) {
                binding.status.text = getText(R.string.warning_status)
                binding.reset.visibility = View.VISIBLE
                binding.status.setTextColor(getColorStateList(R.color.red))
            }
            binding.minus.isEnabled = true
        }
        binding.minus.setOnClickListener {
            counter--
            binding.status.text = "$averageStatus ${50 - counter}"
            binding.counter.text = counter.toString()
            if (counter == 0) {
                binding.minus.isEnabled = false
                binding.status.text = getText(R.string.default_status)
                binding.status.setTextColor(getColorStateList(R.color.green))
            }
        }
        binding.reset.setOnClickListener {
            counter = 0
            binding.counter.text = counter.toString()
            binding.status.text = getText(R.string.default_status)
            binding.reset.visibility = View.INVISIBLE
            binding.minus.isEnabled = false
            binding.status.setTextColor(getColorStateList(R.color.green))
        }
    }
}