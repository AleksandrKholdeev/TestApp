package com.example.testapp.presentation.viewModel

import android.util.Log
import androidx.lifecycle.*
import com.example.testapp.model.repositories.NewsRepository
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onEach
import java.util.concurrent.CancellationException

private const val DEBOUNCE_DELAY_TIME_MS = 500L

class NewsViewModelImpl(val newsRepository: NewsRepository) : ViewModel() {

    val queryChannel = BroadcastChannel<String>(Channel.CONFLATED)
    val searchState = MutableLiveData<SearchState>()
    var page: Int = 1

    val searchResult = queryChannel
        .asFlow()
        .onEach { searchState.value = Loading }
        .mapLatest {
            try {
                val result = newsRepository.searchNews(page++)
                if (result.isEmpty()) {
                    EmptyResult
                } else {
                    ValidResult(result)
                }
            } catch (e: Throwable) {
                if (e is CancellationException) {
                    throw e
                } else {
                    Log.w(NewsViewModelImpl::class.java.name, e)
                    ErrorResult(e)
                }
            }
        }
        .onEach { searchState.value = Ready }
        .catch { emit(TerminalError) }
        .asLiveData(viewModelScope.coroutineContext)

    @Suppress("UNCHECKED_CAST")
    class Factory(private val repo: NewsRepository) :
        ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return NewsViewModelImpl(newsRepository = repo) as T
        }
    }

}