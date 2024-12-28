package com.example.rxjavaexample.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rxjavaexample.databinding.ActivityMainBinding
import com.example.rxjavaexample.di.DaggerAppComponent
import com.example.rxjavaexample.presentation.viewmodels.MainViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val model: MainViewModel = DaggerAppComponent.create().mainViewModel()
    private val adapter: CountryAdapter = CountryAdapter()
    private val compositeDisposable = CompositeDisposable()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.recycler.adapter = adapter

        setContentView(binding.root)

        model.getCountryes()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {adapter.setComplexData(it.data)
                binding.text.text = it.data.toString()},
                {Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()}
            ).also {compositeDisposable.add(it)}
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }


}