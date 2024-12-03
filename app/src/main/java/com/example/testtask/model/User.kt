package com.example.testtask.model

data class User(
    val id: String,
    val title: String,          // Title like "mr", "ms", etc.
    val firstName: String,
    val lastName: String,
    val picture: String         // URL of the user's profile picture
)
