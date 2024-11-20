package com.example.recyclerviewexample.remoterepository

import com.example.recyclerviewexample.model.PhotoList
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/"
const val API_KEY = "ln8mOel9Ps2uKDhEeQAfciRFbMkee3SAJmwYqDqV"
//photos?sol=1000&api_key=YOUR_API_KEY

class RemoteRepository {
    companion object{
        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()))
            .build()

        var nasaApi: NasaApi = retrofit.create(NasaApi::class.java)
    }
}

interface NasaApi{

    @GET("photos?earth_date=2022-1-1")
    suspend fun getPhotos(@Query("api_key") apiKey: String = API_KEY): PhotoList
}