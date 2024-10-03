package com.example.textsaver

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.textsaver.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var repository:Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        repository = Repository(this)

        binding.savedText.text = repository.getText().toString()

        binding.saveButton.setOnClickListener {
            repository.saveText(binding.enterText.text.toString())
            binding.savedText.text = repository.getText()
        }

        binding.clearButton.setOnClickListener {
            repository.clearText()
            binding.savedText.text = ""
        }
    }
}