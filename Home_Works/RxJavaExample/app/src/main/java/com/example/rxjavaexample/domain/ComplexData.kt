package com.example.rxjavaexample.domain

import com.google.gson.annotations.SerializedName

data class ComplexData (

    @SerializedName("pagination" ) var pagination : Pagination?     = Pagination(),
    @SerializedName("data"       ) var data       : ArrayList<Data> = arrayListOf()

)