package com.example.permissionsexample.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.permissionsexample.model.PathToImage
import kotlinx.coroutines.flow.Flow

@Dao
interface PathToImageDAO {

    @Query(value = "SELECT * FROM images")
    fun getAll(): Flow<List<PathToImage>>

    @Insert
    suspend fun add(pathToImage: PathToImage)

    @Delete
    suspend fun delete(pathToImage: PathToImage)
}