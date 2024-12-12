package com.example.rxjavaexample.repository

import com.example.rxjavaexample.domain.ComplexData

interface Repository {

    fun getCurrencies(): ComplexData

}