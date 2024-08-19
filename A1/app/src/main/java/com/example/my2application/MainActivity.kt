package com.example.my2application

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.my2application.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var counter = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)

        //setContentView(R.layout.activity_main)

//        val centralLabel:TextView = findViewById(R.id.labelOnFirstView)
//        val footLabel:TextView = findViewById(R.id.footLabel)

        binding.labelOnFirstView.text = counter.toString()
        setContentView(binding.root)

        binding.mainButton.setOnClickListener {
            binding.labelOnFirstView.text = "Кнока нажата"
            binding.plusOneButton.isEnabled = false
        }

        binding.plusOneButton.setOnClickListener {
            if (counter == 5){
                counter = 0
                binding.mainButton.visibility = View.VISIBLE
            }else{
                counter++
            }
            binding.labelOnFirstView.text = counter.toString()
        }
    }
}