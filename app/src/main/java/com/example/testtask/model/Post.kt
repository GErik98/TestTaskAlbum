package com.example.testtask.model


data class Post(
    val id: String,
    val text: String,  // Ensure this property is defined
    val image: String,
    val date: String,
    val likes: Int,
    val link: String,
    val tags: List<String>,
    val publishDate: String,
    val owner: User
)

data class PostListResponse(
    val data: List<Post> // "data" holds the list of posts
)
