package com.example.exampemv

import androidx.core.content.ContextCompat

sealed class State(
    val isSearching: Boolean = false,
    val result: String? = null
) {
    object waiting : State(result = ContextCompat.getString(App.appContext, R.string.result_search_default))
    object running : State(isSearching = true, result = ContextCompat.getString(App.appContext, R.string.searhing))
    class done(result: String) : State(isSearching = false, result = result)
}