package com.example.retrofit.network

import com.example.retrofit.model.NewsDTO
import com.example.retrofit.model.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    fun getAllUsers(): Call<List<User>>

    @GET("v2/top-headlines")
    fun getAllNews(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String = "8056d2d8ec904cbeaeadf807a9e509fa"
    ): Call<NewsDTO>
}