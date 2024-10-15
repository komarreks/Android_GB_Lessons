package com.example.exampemv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ViewModel: ViewModel() {

    private val _state = MutableStateFlow<State>(State.Waiting)
    val state = _state.asStateFlow()

    private val _resultSearch = Channel<String>()
    val resultSearch = _resultSearch.receiveAsFlow()

    fun onSearch(searchText: String){
        viewModelScope.launch {
            _state.value = State.Running
            delay(5_000)
            _state.value = State.Waiting
            _resultSearch.send( "NOTHING WAS FOUND FOR THR QUERY"+ " '" + searchText + "'")
        }
    }

}