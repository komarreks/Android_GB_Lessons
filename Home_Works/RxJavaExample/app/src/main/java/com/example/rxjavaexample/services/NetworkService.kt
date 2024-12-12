package com.example.rxjavaexample.services

import com.example.rxjavaexample.domain.ComplexData

interface NetworkService {

    fun getCurrencies(): ComplexData

}