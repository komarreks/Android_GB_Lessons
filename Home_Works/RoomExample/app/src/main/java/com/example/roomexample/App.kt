package com.example.roomexample

import android.app.Application
import androidx.room.Room
import com.example.roomexample.database.AppDataBase

class App:Application() {

    //lateinit var dataBase: AppDataBase

    override fun onCreate() {
        super.onCreate()

//        dataBase = Room.databaseBuilder(
//            applicationContext,
//            AppDataBase::class.java,
//            "db"
//        ).build()
        dataBase = Room.inMemoryDatabaseBuilder(
            applicationContext,
            AppDataBase::class.java
        ).build()
    }

    companion object{
        lateinit var dataBase: AppDataBase
    }
}