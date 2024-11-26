package com.example.permissionsexample.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "images")
data class PathToImage(
    @ColumnInfo(name = "name")
    val name:String,
    @ColumnInfo(name = "path")
    var path: String) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
        set(value) {field = value}
}