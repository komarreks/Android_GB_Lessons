package com.example.exampemv

import java.util.concurrent.Flow.Publisher

object Actions {

    @JvmStatic
    fun onSearchTextChanged(text: String, vm: ViewModel){
        if (text.length > 3){
            vm.onSearch(text)
        }
    }
}