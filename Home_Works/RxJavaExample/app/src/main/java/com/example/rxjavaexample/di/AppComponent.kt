package com.example.rxjavaexample.di

import com.example.rxjavaexample.presentation.viewmodels.MainViewModel
import dagger.Component

@Component(
    modules = [
        PresentationModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent {

    fun mainViewModel(): MainViewModel

}