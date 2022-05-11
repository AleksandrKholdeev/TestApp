package com.example.testapp.model.network

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface NewsApi {

    @GET
    suspend fun searchNews(
        @Url url: String,
        @Query("page") page: Int
    ): List<NewsNetworkModel>

}