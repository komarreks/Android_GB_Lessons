package com.example.roomexample

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import com.example.roomexample.database.Letter
import com.example.roomexample.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val vm = Model()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.inputText.setText(vm.letter)

        enableAddButton()

        initInput()

        initButtons()

        lifecycleScope.launch {
            vm.allLetters.collect { letters ->
                binding.lines.text = letters.joinToString(
                    separator = "\r\n"
                )
            }
        }
    }

    private fun enableAddButton(){
        binding.addButton.isEnabled = binding.inputText.text?.length!! > 3
    }

    private fun initInput(){
        binding.inputText.doOnTextChanged { text, start, before, count ->
            enableAddButton()
        }
    }

    private fun initButtons(){
        binding.addButton.setOnClickListener {
            if (isMath(binding.inputText.text.toString())){
                vm.add(Letter(binding.inputText.text.toString(),1))
                binding.inputText.setText("")
            }else{
                Toast.makeText(applicationContext,"Слово содержит недопустимые символы",Toast.LENGTH_LONG).show()
            }

        }
        binding.clearButton.setOnClickListener {
            vm.clear()
        }
    }

    private fun isMath(text: String): Boolean{
        val regex = "[-\\w]+".toRegex()
        val math = regex.matches(text)
        return math
    }
}