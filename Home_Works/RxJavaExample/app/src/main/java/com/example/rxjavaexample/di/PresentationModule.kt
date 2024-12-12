package com.example.rxjavaexample.di

import com.example.rxjavaexample.presentation.viewmodels.MainViewModel
import com.example.rxjavaexample.repository.Repository
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {

    @Provides
    fun provideMainViewModel(repository: Repository): MainViewModel{
        return MainViewModel(repository)
    }

}