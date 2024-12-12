package com.example.rxjavaexample.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.rxjavaexample.domain.ComplexData
import com.example.rxjavaexample.repository.Repository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    fun getCountryes(): Observable<ComplexData> = Observable.create{
        val result = repository.getCurrencies()
        it.onNext(result)
    }.subscribeOn(Schedulers.io())


}