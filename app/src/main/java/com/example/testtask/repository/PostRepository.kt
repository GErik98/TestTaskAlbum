package com.example.testtask.repository

import com.example.testtask.BuildConfig
import com.example.testtask.model.Post
import com.example.testtask.model.PostListResponse
import com.example.testtask.network.ApiService
import retrofit2.Response

class PostRepository(private val apiService: ApiService) {
    suspend fun getPosts(): Response<PostListResponse> {
        return apiService.getPosts(BuildConfig.API_KEY)  // This should match the ApiService method
    }
}
