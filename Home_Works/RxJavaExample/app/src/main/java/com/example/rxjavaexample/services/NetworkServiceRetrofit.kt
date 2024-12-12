package com.example.rxjavaexample.services

import com.example.rxjavaexample.domain.ComplexData
import retrofit2.Retrofit
import retrofit2.http.GET

const val BASE_URL = "https://api.marketstack.com/v1/"
const val API_KEY = "41bfc0a15ad4fb520e0003717f3c6467"

class NetworkServiceRetrofit: NetworkService {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .build()

    val api: Api = retrofit.create(Api::class.java)

    override fun getCurrencies(): ComplexData {
        return api.getCurrencies()
    }
}

interface Api{
    @GET("currencies?access_key=${API_KEY}")
    fun getCurrencies(): ComplexData
}