package com.example.exampemv

import android.app.Application
import android.content.Context

class App:Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = this
    }

    companion object{
        lateinit var appContext: Context
    }

}