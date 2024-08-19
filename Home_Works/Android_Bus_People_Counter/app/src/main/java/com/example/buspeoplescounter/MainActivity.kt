package com.example.buspeoplescounter

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.example.buspeoplescounter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var countOfPeople = 0
    val MAX_BUS_SEATS = 49
    lateinit var binding:ActivityMainBinding

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        emptyBus()

        binding.plusButton.setOnClickListener {
            countOfPeople++
            if (countOfPeople > MAX_BUS_SEATS){
                binding.statusLabel.setTextColor(getColor(R.color.red))
                binding.statusLabel.text = baseContext.getText(R.string.full_bus)
            }else{
                updateMessage()
            }
            enableAlarmButtons()
        }

        binding.minusButton.setOnClickListener {
            if (countOfPeople>0)countOfPeople--
            updateMessage()
            enableAlarmButtons()

            if (countOfPeople == 0){
                emptyBus()
            }
        }

        binding.resetButton.setOnClickListener {
            countOfPeople = 0
            emptyBus()
        }
    }

    fun emptyBus(){
        binding.statusLabel.text = baseContext.getText(R.string.empty_bus)
        binding.statusLabel.setTextColor(getColor(R.color.green))
        enableAlarmButtons()
    }

    fun updateMessage(){
        binding.statusLabel.setTextColor(getColor(R.color.blue1))
        binding.statusLabel.text = "${getText(R.string.places_left)} ${MAX_BUS_SEATS - countOfPeople}"
    }

    fun enableAlarmButtons(){
        binding.minusButton.isEnabled = countOfPeople>0
        binding.resetButton.isVisible = countOfPeople>MAX_BUS_SEATS
    }
}