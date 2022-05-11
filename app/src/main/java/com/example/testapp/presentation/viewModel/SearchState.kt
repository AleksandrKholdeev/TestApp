package com.example.testapp.presentation.viewModel

sealed class SearchState
object Loading : SearchState()
object Ready : SearchState()