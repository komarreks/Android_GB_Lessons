package com.example.examplerest.domain

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserList(
    @Json(name="results") val users: List<User>) {
}