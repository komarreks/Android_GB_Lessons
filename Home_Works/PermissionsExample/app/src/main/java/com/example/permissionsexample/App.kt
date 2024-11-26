package com.example.permissionsexample

import android.app.Application
import androidx.room.Room
import com.example.permissionsexample.database.daos.PathToImageDAO

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(
            applicationContext,
            com.example.permissionsexample.database.Database::class.java,
            "database"
        ).build()

        pathToImageDAO = db.PathToImageDAO()
    }

    companion object{
        lateinit var db: com.example.permissionsexample.database.Database
        lateinit var pathToImageDAO: PathToImageDAO

        var permissionsGranted: Boolean = false
    }
}