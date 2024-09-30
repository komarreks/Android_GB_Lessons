package com.example.timer

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.timer.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    private var handler = Handler(Looper.getMainLooper())
    var work:Boolean = false
    var count:Int = 0
    var startCount:Int = count
    var timerRunning:Boolean = false

    lateinit var timerThread:Thread

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        if (savedInstanceState == null){
            startInitValues()
        }else{
            count = savedInstanceState.getInt("count")
            work = savedInstanceState.getBoolean("work")
            timerRunning = savedInstanceState.getBoolean("timerRunning")
            startCount = savedInstanceState.getInt("startCount")
        }

        initSlider()
        initStartStopButton()
        initResetButton()
        updateUI()

        if (savedInstanceState != null){
            startTimer()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("count", count)
        outState.putBoolean("work", work)
        outState.putBoolean("timerRunning", timerRunning)
        outState.putInt("startCount", startCount)
        super.onSaveInstanceState(outState)
    }

    private fun startInitValues(){
        count = resources.getInteger(R.integer.defoult_counter_value)
        startCount = count
    }

    private fun updateUI(){
        binding.counter.text = count.toString()
        binding.countSlider.isEnabled = !work && !timerRunning
        setStartButtonText()
        binding.resetButton.isVisible = !work && timerRunning
        binding.countProgress.progress = count
    }

    private fun initStartStopButton(){
        setStartButtonText()

        binding.startButton.setOnClickListener {
            work = !work
            timerRunning = true
            setStartButtonText()

            when(work){
                true -> startTimer()
                false -> stopTimer()
            }
        }
    }

    private fun initResetButton(){
        binding.resetButton.isVisible = false

        binding.resetButton.setOnClickListener {
            resetCount()
            updateUI()
        }
    }

    private fun resetCount(){
        count = resources.getInteger(R.integer.defoult_counter_value)
        timerRunning = false
        binding.countSlider.value = count.toFloat()
        updateUI()
    }

    private fun stopTimer(){
        work = false
        showSnakbar(getString(R.string.timer_is_stopped))
        updateUI()
    }

    private fun showSnakbar(text: String){
        Snackbar.make(binding.root, text,Snackbar.LENGTH_LONG).show()
    }

    private fun startTimer(){
        timerRunning = true
        timerThread = Thread {
            while (work) {
                count--
                handler.post { updateUI()}
                Thread.sleep(1_000)
            }

            if (count == 0){
                timerRunning = false
                showSnakbar(getString(R.string.timer_is_done))
                resetCount()
                updateUI()
            }
        }

        timerThread.start()
    }

    private fun setStartButtonText(){
        if (work){
            binding.startButton.text = getText(R.string.text_stop)
        }else if (!work && timerRunning){
            binding.startButton.text = getText(R.string.text_resume)
        }else{
            binding.startButton.text = getText(R.string.text_start)
        }
    }

    private fun initSlider(){
        binding.countSlider.valueFrom = resources.getInteger(R.integer.min_value_slider).toFloat()
        binding.countSlider.valueTo   = resources.getInteger(R.integer.max_value_slider).toFloat()
        binding.countSlider.stepSize  = binding.countSlider.valueTo/resources.getInteger(R.integer.step_slider).toFloat()
        binding.countSlider.value     = startCount.toFloat()

        binding.countProgress.max = startCount

        binding.countSlider.addOnChangeListener { slider, value, fromUser ->
            count = value.toInt()
            startCount = count
            binding.countProgress.max = startCount
            updateUI()
        }
    }
}