package com.example.rxjavaexample.repository

import com.example.rxjavaexample.domain.ComplexData
import com.example.rxjavaexample.services.NetworkService

class RemoteRepositoryImpl(val networkService: NetworkService): Repository {

    override fun getCurrencies(): ComplexData {
        return networkService.getCurrencies()
    }
}