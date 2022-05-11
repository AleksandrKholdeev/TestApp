package com.example.testapp.model.repositories

import android.util.Log
import com.example.testapp.model.entities.News
import com.example.testapp.model.network.NewsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext

class NewsRepository(private val api: NewsApi) {

    internal suspend fun searchNews(page: Int = 0): List<News> {
        return withContext(Dispatchers.IO) {
            flowOf(api.searchNews("http://188.40.167.45:3001/", page))

        }
            .flowOn(Dispatchers.IO)
            .onEach { Log.d(NewsRepository::class.java.name, it.toString()) }
            .flatMapConcat { it.asFlow() }
            .map {
                News(
                    it.newsTitle ?: "",
                    it.type ?: "",
                    it.imgUrl ?: "",
                    it.websiteUrl ?: "",
                    it.postedTime ?: "",
                    when (it.isTop) {
                        0 -> false
                        else -> true
                    }
                )
            }
            .toList()
    }

}