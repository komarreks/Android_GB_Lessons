package com.example.permissionsexample.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.permissionsexample.database.daos.PathToImageDAO
import com.example.permissionsexample.model.PathToImage

@Database(entities = [PathToImage::class], version = 1)
abstract class Database: RoomDatabase() {

    abstract fun PathToImageDAO(): PathToImageDAO

}