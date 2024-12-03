package com.example.testtask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtask.model.Post
import com.example.testtask.repository.PostRepository
import kotlinx.coroutines.launch

class PostViewModel(private val repository: PostRepository) : ViewModel() {

    // LiveData to hold the list of posts
    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> get() = _posts

    // LiveData to handle errors
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    // Function to fetch posts from the repository
    fun fetchPosts() {
        viewModelScope.launch {
            try {
                val response = repository.getPosts()
                if (response.isSuccessful && response.body() != null) {
                    _posts.value = response.body()?.data
                } else {
                    _error.value = "Error: ${response.code()} ${response.message()}"
                }
            } catch (e: Exception) {
                _error.value = "Exception: ${e.message}"
            }
        }
    }
}
