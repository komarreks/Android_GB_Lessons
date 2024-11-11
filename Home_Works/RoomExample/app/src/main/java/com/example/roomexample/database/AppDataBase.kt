package com.example.roomexample.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [
    Letter::class
],
    version = 1)
abstract class AppDataBase:RoomDatabase() {
    abstract fun letterDao(): LetterDao
}