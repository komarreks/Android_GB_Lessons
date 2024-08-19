package com.example.layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.layout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.post2.setTopText("верхняя строчка, настроенная из кода")
        binding.post2.setBottomText("нижняя строчка, настроенная из кода")
    }
}