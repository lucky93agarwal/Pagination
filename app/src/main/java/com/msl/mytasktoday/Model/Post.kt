package com.msl.mytasktoday.Model


// Post.kt
data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)