package com.example.rxjavaexample.services

import com.example.rxjavaexample.domain.ComplexData
import com.google.gson.Gson
import com.google.gson.JsonObject
import io.reactivex.rxjava3.core.Observable
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://api.marketstack.com/v1/"
const val API_KEY = "a2a609cda1605471294339ab32923840"

class NetworkServiceRetrofit: NetworkService {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    val api: Api = retrofit.create(Api::class.java)

    override fun getCurrencies(): ComplexData {

        var complexData = ComplexData()

        var body = api.getCurrencies().execute().body()?.string()

        complexData = Gson().fromJson(body, ComplexData::class.java)

        return complexData
    }

    companion object{

    }
}

interface Api{
    @GET("currencies?access_key=a2a609cda1605471294339ab32923840")
    fun getCurrencies(): Call<ResponseBody>
}