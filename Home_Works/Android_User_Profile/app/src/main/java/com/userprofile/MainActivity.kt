package com.userprofile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doOnTextChanged
import com.google.android.material.snackbar.Snackbar
import com.userprofile.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        startInit()

        binding.loginText.doOnTextChanged { text, start, before, count ->
            enabledSaveButton()
        }

        binding.phoneText.doOnTextChanged { text, start, before, count ->
            enabledSaveButton()
        }

        binding.variantMale.setOnCheckedChangeListener { compoundButton, isChecked ->
            enabledSaveButton()
        }

        binding.variantFemale.setOnCheckedChangeListener { compoundButton, isChecked ->
            enabledSaveButton()
        }

        binding.swithBox.setOnCheckedChangeListener { button, isChecked ->
            enabledChecked(isChecked)
        }

        binding.chekAutorization.setOnCheckedChangeListener { compoundButton, isChecked ->
            enabledSaveButton()
        }

        binding.chekGetNews.setOnCheckedChangeListener{ compoundButton, isChecked ->
            enabledSaveButton()}

        binding.saveButton.setOnClickListener {
            Snackbar.make(it, R.string.saved,Snackbar.LENGTH_SHORT).show()
        }
    }

    fun startInit(){
        enabledChecked(false)
        setPoints(Random.nextInt(101))
        enabledSaveButton()
    }

    fun setPoints(points: Int){
        binding.lineProgress.progress = points
        binding.textPoints.text = "$points/100"
    }

    fun enabledSaveButton(){
        val isNameCorrect = binding.loginText.text!!.length in 1..39
        val isPhoneCorrect = binding.phoneText.text!!.isNotEmpty()
        val isSexChecked = binding.variantMale.isChecked || binding.variantFemale.isChecked
        val enabledNotifyVariant = binding.chekAutorization.isChecked || binding.chekGetNews.isChecked

        binding.saveButton.isEnabled = isNameCorrect && isPhoneCorrect && isSexChecked && enabledNotifyVariant
    }

    fun enabledChecked(checked: Boolean){
        binding.chekAutorization.isEnabled = checked
        binding.chekGetNews.isEnabled = checked

        if (!checked){
            binding.chekAutorization.isChecked = false
            binding.chekGetNews.isChecked = false
        }
    }
}