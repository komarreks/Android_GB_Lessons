package com.example.rxjavaexample.domain

import com.google.gson.annotations.SerializedName

data class Data (

  @SerializedName("code"   ) var code   : String? = null,
  @SerializedName("symbol" ) var symbol : String? = null,
  @SerializedName("name"   ) var name   : String? = null

)