package com.example.testtask.network

import com.example.testtask.BuildConfig
import com.example.testtask.model.Post
import com.example.testtask.model.PostListResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {

    @GET("post")
    suspend fun getPosts(
        @Header("app-id") apiKey: String  // API key included in header
    ): Response<PostListResponse>  // Return the wrapper object
}
private const val BASE_URL = "https://dummyapi.io/data/v1/"
private const val API_KEY = BuildConfig.API_KEY // Replace with your actual API key
object ApiServiceFactory {



    fun create(): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        return apiService
    }
}

// Now, call the API with the key included:
suspend fun getPostsFromApi(): List<Post>? {
    val apiService = ApiServiceFactory.create()

    val response = apiService.getPosts(API_KEY) // Pass API key here when calling the method
    if (response.isSuccessful) {
        return response.body()?.data
    } else {
        // Handle failure (e.g., 404 error)
        return null
    }
}
