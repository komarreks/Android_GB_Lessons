package com.example.roomexample.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface LetterDao {

    @Query("SELECT * FROM letters WHERE word like :text")
    suspend fun getLetter(text:String): Letter

    @Query("SELECT * FROM letters")
    fun getLetters():Flow<List<Letter>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveLetter(letter: Letter)

    @Update
    suspend fun updateLetter(letter: Letter)

    @Query("DELETE FROM letters")
    suspend fun clearLetters()

}