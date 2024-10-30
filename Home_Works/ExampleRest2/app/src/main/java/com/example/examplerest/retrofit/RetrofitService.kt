package com.example.examplerest.retrofit




import com.example.examplerest.domain.UserList
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

object RetrofitService {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://randomuser.me")
        .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()))
        .build()
    val randomUserApi: RandomUserApi = retrofit.create(RandomUserApi::class.java)
}

interface RandomUserApi{
    @GET("/api/?inc=name,gender,email,phone,picture&noinfo")
    suspend fun getRandomUsers(): UserList
}