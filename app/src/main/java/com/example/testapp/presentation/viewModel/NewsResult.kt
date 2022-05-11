package com.example.testapp.presentation.viewModel

import com.example.testapp.model.entities.News

sealed class NewsResult
data class ValidResult(val result: List<News>) : NewsResult()
object EmptyResult : NewsResult()
object EmptyQuery : NewsResult()
data class ErrorResult(val e: Throwable) : NewsResult()
object TerminalError : NewsResult()
