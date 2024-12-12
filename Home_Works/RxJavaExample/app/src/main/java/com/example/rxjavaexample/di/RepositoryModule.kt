package com.example.rxjavaexample.di

import com.example.rxjavaexample.repository.RemoteRepositoryImpl
import com.example.rxjavaexample.repository.Repository
import com.example.rxjavaexample.services.NetworkService
import com.example.rxjavaexample.services.NetworkServiceRetrofit
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun getNetworkService(): NetworkService{
        return NetworkServiceRetrofit()
    }

    @Provides
    fun getRepository(networkService: NetworkService): Repository{
        return RemoteRepositoryImpl(networkService)
    }

}