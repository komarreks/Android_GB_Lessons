package com.example.permissionsexample.presentation

import androidx.lifecycle.ViewModel
import com.example.permissionsexample.App
import com.example.permissionsexample.model.PathToImage
import kotlinx.coroutines.flow.Flow

class Model: ViewModel() {

    private var photoDao = App.pathToImageDAO

    val photos: Flow<List<PathToImage>> = photoDao.getAll()
}