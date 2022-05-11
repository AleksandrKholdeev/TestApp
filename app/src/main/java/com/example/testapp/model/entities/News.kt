package com.example.testapp.model.entities

data class News(
    val newsTitle: String,
    val type: String,
    val imgUrl: String,
    val websiteUrl: String,
    val postedTime: String,
    val isTop: Boolean,
)