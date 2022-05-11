package com.example.testapp.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.testapp.model.entities.News
import kotlinx.coroutines.channels.SendChannel

abstract class NewsViewModel : ViewModel() {

    abstract val queryChannel: SendChannel<String>

    abstract val searchResult: LiveData<News>
    abstract val searchState: LiveData<News>

}