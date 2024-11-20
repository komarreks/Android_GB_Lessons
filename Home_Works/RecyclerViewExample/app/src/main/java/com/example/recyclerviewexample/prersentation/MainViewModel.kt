package com.example.recyclerviewexample.prersentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recyclerviewexample.model.Photo
import com.example.recyclerviewexample.remoterepository.RemoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val _photoList = MutableStateFlow<List<Photo>>(listOf())
    val photoList = _photoList.asStateFlow()

    init {
        updateData()
    }

    fun updateData(){
        viewModelScope.launch {
            try {
                _photoList.value = RemoteRepository.nasaApi.getPhotos().photos
                Log.d("load data", _photoList.value.size.toString())
                val a=0
            }catch (ex: Exception){
                //Toast.makeText()
            }
        }
    }
}