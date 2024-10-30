package com.example.examplerest.domain

import com.squareup.moshi.FromJson
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.Date
import kotlin.reflect.KProperty

@JsonClass(generateAdapter = true)
class User(
    @Json(name = "gender") val gender:String,
    @Json(name = "name") val name: Name,
    @Json(name = "email") val email: String,
    @Json(name = "phone") val phone: String,
    @Json(name = "picture") val picture: Picture,
    )


@JsonClass(generateAdapter = true)
data class Name(
    @Json(name = "title") val title:String,
    @Json(name = "first") val first:String,
    @Json(name = "last") val last:String
)

@JsonClass(generateAdapter = true)
data class Picture(
    @Json(name = "large") val large: String,
    @Json(name = "medium") val medium: String,
    @Json(name = "thumbnail") val thumbnail: String
)

