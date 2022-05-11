package com.example.testapp.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.testapp.model.network.NewsApi
import com.example.testapp.model.repositories.NewsRepository
import com.example.testapp.presentation.viewModel.NewsViewModelImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppComponent() {

    private val repository: NewsRepository

    init {
        val api = Retrofit.Builder()
            .baseUrl("http://188.40.167.45:3001/")
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
            .create(NewsApi::class.java)

        repository = NewsRepository(api)
    }

    internal fun getViewModel(fragment: Fragment): NewsViewModelImpl {
        return ViewModelProvider(fragment, NewsViewModelImpl.Factory(repository))
            .get(NewsViewModelImpl::class.java)
    }

}