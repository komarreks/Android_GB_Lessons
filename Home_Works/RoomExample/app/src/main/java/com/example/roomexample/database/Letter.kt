package com.example.roomexample.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "letters")
data class Letter(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "word")
    val word: String,
    @ColumnInfo(name = "count")
    var count:Int
)
