package com.example.exampemv

import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class ViewModel: ViewModel() {

    private val _state = MutableStateFlow<State>(State.waiting)
    val state = _state.asStateFlow()

    private var _searchText = MutableStateFlow<String>("")
    val searchText get() = _searchText.value

    fun onSearch(textSearch: String){
        viewModelScope.coroutineContext.cancelChildren()

        _searchText.value = textSearch

        _searchText.debounce(1_000).onEach {
            viewModelScope.launch {
                _state.value = State.running
                delay(5_000)
                _state.value = State.done(ContextCompat.getString(App.appContext, R.string.result_search_text) + " '" + searchText + "'")
            }
        }.launchIn(viewModelScope)
    }

}