package com.example.testapp.model.network

import com.google.gson.annotations.SerializedName

data class NewsNetworkModel(
    @SerializedName("title")
    val newsTitle: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("img")
    val imgUrl: String?,
    @SerializedName("click_url")
    val websiteUrl: String?,
    @SerializedName("time")
    val postedTime: String?,
    @SerializedName("top")
    val isTop: Int?,
)
