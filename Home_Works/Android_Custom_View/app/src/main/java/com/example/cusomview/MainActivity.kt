package com.example.cusomview

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cusomview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)


        binding.postWindow.setTopText("Это самая верхняя надпись")
        binding.postWindow.setDownText("Это самая нижняя запись")

        setContentView(binding.root)
    }
}