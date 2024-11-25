package com.dicoding.freshfind.network

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("home")
    suspend fun getProducts(): ProductResponse
}