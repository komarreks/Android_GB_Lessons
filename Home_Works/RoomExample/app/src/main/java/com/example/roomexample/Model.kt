package com.example.roomexample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomexample.database.Letter
import com.example.roomexample.database.LetterDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class Model():ViewModel() {
    private var _letter = ""
    val letter get() = _letter
    private var letterDao:LetterDao = App.dataBase.letterDao()

    val allLetters: Flow<List<Letter>> = this.letterDao.getLetters()

    fun saveLetter(text: String){
        _letter = text
    }

    fun add(letter: Letter){
        viewModelScope.launch {
            val letterInDb = letterDao.getLetter(letter.word)

            if (letterInDb == null){
                letterDao.saveLetter(letter)
            }else{
                letterInDb.count += 1
                letterDao.updateLetter(letterInDb)
            }
        }
    }

    fun clear(){
        viewModelScope.launch {
            letterDao.clearLetters()
        }
    }

}

