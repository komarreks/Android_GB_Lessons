package com.example.rxjavaexample.domain

import com.google.gson.annotations.SerializedName

data class Pagination (

  @SerializedName("limit"  ) var limit  : Int? = null,
  @SerializedName("offset" ) var offset : Int? = null,
  @SerializedName("count"  ) var count  : Int? = null,
  @SerializedName("total"  ) var total  : Int? = null

)