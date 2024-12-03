package com.example.testtask.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testtask.network.ApiService
import com.example.testtask.repository.PostRepository

class PostViewModelFactory(private val apiService: ApiService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostViewModel::class.java)) {
            return PostViewModel(PostRepository(apiService)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

