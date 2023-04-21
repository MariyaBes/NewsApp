package com.example.newsapp.data.api

import androidx.room.Query
import com.example.newsapp.utils.Constants.Companion.API_KEY
import retrofit2.http.GET

interface NewsService {

    @GET("/v2/everything")

    suspend fun getEverything(
        @retrofit2.http.Query("q") query: String,
        @retrofit2.http.Query("page") page: Int = 1,
        @retrofit2.http.Query("apiKey") apiKey: String = API_KEY
    )

    suspend fun getHeadlines(
        @retrofit2.http.Query("category") category: String = "technology",
        @retrofit2.http.Query("page") page: Int = 1,
        @retrofit2.http.Query("apiKey") apiKey: String = API_KEY
    )
}